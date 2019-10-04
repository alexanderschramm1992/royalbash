package de.schramm.royalbash.infrastructure

import de.schramm.royalbash.application.GameService
import de.schramm.royalbash.application.Games
import de.schramm.royalbash.application.UUIDGenerator
import de.schramm.royalbash.infrastructure.database.GamePersistenceMapper
import de.schramm.royalbash.infrastructure.database.GamePersistenceOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
class BeanConfig {

    @Bean
    fun uuidGenerator(): UUIDGenerator = RandomUUIDGenerator

    @Bean
    fun games(persistenceOperations: GamePersistenceOperations): Games =
            GamePersistenceMapper(persistenceOperations)

    @Bean
    fun gameService(uuidGenerator: UUIDGenerator, games: Games): GameService =
            GameService(uuidGenerator, games)

    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
}
