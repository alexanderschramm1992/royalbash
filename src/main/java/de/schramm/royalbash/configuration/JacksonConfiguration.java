package de.schramm.royalbash.configuration;

import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootConfiguration
public class JacksonConfiguration {

    private UUIDDeserializer uuidDeserializer = new UUIDDeserializer();

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer addObjectMapper() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.deserializerByType(
                UUID.class,
                uuidDeserializer
        );
    }
}
