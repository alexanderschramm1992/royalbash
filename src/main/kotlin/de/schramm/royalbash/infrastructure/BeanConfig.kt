package de.schramm.royalbash.infrastructure

import de.schramm.royalbash.application.GameService
import de.schramm.royalbash.application.Games
import de.schramm.royalbash.domain.UUIDGenerator
import de.schramm.royalbash.infrastructure.database.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class BeanConfig {

    private val uuidGenerator = UUIDGenerator()

    @Bean
    open fun uuidGenerator(): UUIDGenerator = uuidGenerator

    @Bean
    open fun games(persistenceOperations: GamePersistenceOperations): Games =
            GamePersistenceMapper(persistenceOperations)

    @Bean
    open fun gameService(uuidGenerator: UUIDGenerator, games: Games): GameService =
            GameService(uuidGenerator, games)
}
