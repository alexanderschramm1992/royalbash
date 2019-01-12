package de.schramm.royalbash.infrastructure;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
class DatabaseConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "MongoDB";
    }

    @Override
    public Mongo mongo() {
        return new Fongo("Fongo").getMongo();
    }
}
