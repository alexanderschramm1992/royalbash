package de.schramm.royalbash.configuration;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class ConverterUUIDToString implements Converter<UUID, String> {

    @Override
    public String convert(UUID uuid) {

        return uuid.toString();
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {

        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {

        return null;
    }
}
