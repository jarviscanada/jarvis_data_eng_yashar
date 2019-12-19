package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;

import java.rmi.MarshalledObject;

import static junit.framework.TestCase.*;
import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

    @Mock
    HttpHelper mockHelper;

    @InjectMocks
    TwitterDao dao;

    @Test
    public void postTweet() throws Exception{
        // test failed request
        String hashTag = "#abc";
        String text = "Unit testing" + hashTag + " " + System.currentTimeMillis();
        Double lat =1d;
        Double lon = -1d;

        // exception
        Mockito.when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
        try{
            dao.create(TweetUtil.buildTweet(text, lon, lat));
            fail();
        }catch(RuntimeException e){
            assertTrue(true);
        }

        // Test happy path
        String tweetJsonStr = "{\n"
                + "  \"created_at\": \"Mon Feb 18 21:24:39 +0000 2019\", \n"
                + "  \"id\": 1097607853932564480, \n"
                + "  \"id_str\":\"1097607853932564480\",\n"
                + "  \"text\":\"text with loc223\",\n"
                + "  \"entities\":{\n"
                + "     \"hashtags\":[],"
                + "     \"user_mentions\":[],"
                + "  },\n"
                + "  \"coordinates\":null,"
                + "  \"retweet_count\":0,\n"
                + "  \"favorite_count\":0,\n"
                + "  \"favorited\":false,\n"
                + "  \"retweeted\":false\n"
                + "}";

        Mockito.when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr,Tweet.class);
        //mock parseResponseBody
        Mockito.doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
        Tweet tweet = spyDao.create(TweetUtil.buildTweet(text,lon,lat));
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void showTweet() throws Exception{
        String hashTag = "#abc";
        String text = "Unit testing" + hashTag + " " + System.currentTimeMillis();
        Double lat =1d;
        Double lon = -1d;

        // exception
        Mockito.when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
        try{
            dao.findById("1097607853932564480");
            fail();
        }catch(RuntimeException e){
            assertTrue(true);
        }

        // Test happy path
        String tweetJsonStr = "{\n"
                + "  \"created_at\": \"Mon Feb 18 21:24:39 +0000 2019\", \n"
                + "  \"id\": 1097607853932564480, \n"
                + "  \"id_str\":\"1097607853932564480\",\n"
                + "  \"text\":\"text with loc223\",\n"
                + "  \"entities\":{\n"
                + "     \"hashtags\":[],"
                + "     \"user_mentions\":[],"
                + "  },\n"
                + "  \"coordinates\":null,"
                + "  \"retweet_count\":0,\n"
                + "  \"favorite_count\":0,\n"
                + "  \"favorited\":false,\n"
                + "  \"retweeted\":false\n"
                + "}";

        Mockito.when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr,Tweet.class);
        //mock parseResponseBody
        Mockito.doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
        Tweet tweet = spyDao.findById("1097607853932564480");
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void deleteTweet() throws Exception{
        String hashTag = "#abc";
        String text = "Unit testing" + hashTag + " " + System.currentTimeMillis();
        Double lat =1d;
        Double lon = -1d;

        // exception
        Mockito.when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
        try{
            dao.deleteById("1097607853932564480");
            fail();
        }catch(RuntimeException e){
            assertTrue(true);
        }
        // Test happy path
        String tweetJsonStr = "{\n"
                + "  \"created_at\": \"Mon Feb 18 21:24:39 +0000 2019\", \n"
                + "  \"id\": 1097607853932564480, \n"
                + "  \"id_str\":\"1097607853932564480\",\n"
                + "  \"text\":\"text with loc223\",\n"
                + "  \"entities\":{\n"
                + "     \"hashtags\":[],"
                + "     \"user_mentions\":[],"
                + "  },\n"
                + "  \"coordinates\":null,"
                + "  \"retweet_count\":0,\n"
                + "  \"favorite_count\":0,\n"
                + "  \"favorited\":false,\n"
                + "  \"retweeted\":false\n"
                + "}";

        Mockito.when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr,Tweet.class);
        //mock parseResponseBody
        Mockito.doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
        Tweet tweet = spyDao.deleteById("1097607853932564480");
        assertNotNull(tweet);
        assertNotNull(tweet.getText());

    }
}
