package ca.jrvs.apps.trading;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;

public class TestConfig {
    @Bean
    public BasicDataSource dataSource(){
        String url = System.getenv("PSQL_URL");
        String user = System.getenv("PSQL_USER");
        String password = System.getenv("PSQL_PASSWORD");

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);

        return basicDataSource;

    }
}