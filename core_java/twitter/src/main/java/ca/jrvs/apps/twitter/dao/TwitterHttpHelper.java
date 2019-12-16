package ca.jrvs.apps.twitter.dao;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.URI;

public class TwitterHttpHelper implements HttpHelper{

    // dependencies are specified as private var
    private OAuthConsumer consumer;
    private DefaultHttpClient httpClient;

    public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret){
        consumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
        consumer.setTokenWithSecret(accessToken,tokenSecret);

        httpClient = new DefaultHttpClient();
    }

    @Override
    public HttpResponse httpPost(URI uri){
        try{
            return executeHttpRequest(HttpMethod.POST, uri, null);
        }catch (OAuthException | IOException e){
            throw new RuntimeException("Failed to execute", e);
        }
    }

    @Override
    public HttpResponse httpGet(URI uri){
        try{
            return executeHttpRequest(HttpMethod.GET, uri, null);
        }catch (OAuthException | IOException e){
            throw new RuntimeException("Failed to execute", e);
        }
    }

    // helper function for executing http Methods
    private HttpResponse executeHttpRequest(HttpMethod method, URI uri, StringEntity stringEntity) throws OAuthException, IOException{
        if(method == HttpMethod.GET){
            HttpGet request = new HttpGet(uri);
            consumer.sign(request);
            return httpClient.execute(request);
        }else if (method == HttpMethod.POST){
            HttpPost request = new HttpPost(uri);
            if(stringEntity != null){
                request.setEntity(stringEntity);
            }
            consumer.sign(request);
            return httpClient.execute(request);
        }else{
            throw new IllegalArgumentException("Unknown HTTP method" + method.name());
        }
    }

    public static void main (String[] args) throws Exception{
        //env var for OAuth
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        System.out.println(consumerKey +"|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);
        // create components
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);

        HttpResponse response = httpHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=first_tweet2"));
        System.out.println(EntityUtils.toString(response.getEntity()));

    }


}
