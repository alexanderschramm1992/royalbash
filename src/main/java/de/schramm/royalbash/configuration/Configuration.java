package de.schramm.royalbash.configuration;

import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.UUID;

@SpringBootConfiguration
public class Configuration {

    private UUIDDeserializer uuidDeserializer = new UUIDDeserializer();

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer addObjectMapper() {
        return new Jackson2ObjectMapperBuilderCustomizer() {

            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                jacksonObjectMapperBuilder.deserializerByType(UUID.class, uuidDeserializer);
            }
        };
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        return loggingFilter;
    }
}
