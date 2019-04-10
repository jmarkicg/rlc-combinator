package hr.markic.rlc.config;

import com.mongodb.MongoClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "hr.markic.rlc.repository")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Configuration
public class DBConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return PropertyConfig.getInstance().getProperty("spring.data.mongodb.database");
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(PropertyConfig.getInstance().getProperty("spring.data.mongodb.host"),
                               PropertyConfig.getInstance().getIntProperty("spring.data.mongodb.port"));
    }
}
