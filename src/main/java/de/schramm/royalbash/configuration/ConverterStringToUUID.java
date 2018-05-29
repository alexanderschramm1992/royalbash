package de.schramm.royalbash.configuration;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class ConverterStringToUUID implements Converter<String, UUID> {


    @Override
    public UUID convert(String string) {

        return UUID.fromString(string);
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
