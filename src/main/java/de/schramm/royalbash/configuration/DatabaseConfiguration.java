package de.schramm.royalbash.configuration;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class DatabaseConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "Fongo";
    }

    @Override
    public Mongo mongo() {
        return new Fongo(getDatabaseName()).getMongo();
    }
}
