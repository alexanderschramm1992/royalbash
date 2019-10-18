package de.schramm.royalbash.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import de.schramm.royalbash.application.gameevent.GameEventDTO
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class JacksonConfig {

    @Bean
    fun objectMapper(builder: Jackson2ObjectMapperBuilder): ObjectMapper {
        println("Building own ObjectMapper")
        val objectMapper = builder.createXmlMapper(false).build<ObjectMapper>()

        objectMapper.registerModule(KotlinModule())
        objectMapper.registerModule(JavaTimeModule())

        objectMapper.addMixIn(GameEventDTO::class.java, GameEventDTOMixin::class.java)

        return objectMapper
    }
}
