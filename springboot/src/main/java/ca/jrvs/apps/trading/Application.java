package ca.jrvs.apps.trading;


import ca.jrvs.apps.trading.service.QuoteService;
import org.hibernate.Hibernate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.util.logging.Logger;

@SpringBootApplication(exclude = {JdbcTemplateAutoConfiguration.class,
        DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Application {
    private Logger logger = (Logger) LoggerFactory.getLogger(Application.class);
    @Value("${app.init.dailyList}")
    private String[] initDailyList;

    @Autowired
    private QuoteService quoteService;

    public static void main(String[] args) throws Exception{
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }

//    @Override
//    public void run(String... args) throws Exception{
//    }
}
