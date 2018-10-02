package de.schramm.royalbash;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Log4j2
@Configuration
class DatabaseConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "MongoDB";
    }

    @Override
    public Mongo mongo() {

        Mongo mongo = new Fongo("Fongo").getMongo();

        log.info("Started MongoDB (Fongo)");

        return mongo;
    }
}
