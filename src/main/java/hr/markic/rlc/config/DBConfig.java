package hr.markic.rlc.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "hr.markic.rlc.repository")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Configuration
public class DBConfig extends AbstractMongoConfiguration {

    Environment environment;
    PropertyConfig propertyConfig;

    @Autowired
    public DBConfig(Environment env, PropertyConfig propC){
        this.environment = env;
        this.propertyConfig = propC;
    }

    @Override
    protected String getDatabaseName() {
        return environment.getProperty("spring.data.mongodb.database");
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(propertyConfig.getProperty("spring.data.mongodb.host"), propertyConfig.getIntProperty("spring.data.mongodb.port"));
    }
}
