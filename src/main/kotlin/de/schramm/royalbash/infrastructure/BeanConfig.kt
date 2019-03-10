package de.schramm.royalbash.infrastructure

import de.schramm.royalbash.application.GameService
import de.schramm.royalbash.application.Games
import de.schramm.royalbash.domain.UUIDGenerator
import de.schramm.royalbash.infrastructure.database.GamePersistenceMapper
import de.schramm.royalbash.infrastructure.database.GamePersistenceOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig {

    private val uuidGenerator = UUIDGenerator()

    @Bean
    fun uuidGenerator(): UUIDGenerator = uuidGenerator

    @Bean
    fun games(persistenceOperations: GamePersistenceOperations): Games =
            GamePersistenceMapper(persistenceOperations)

    @Bean
    fun gameService(uuidGenerator: UUIDGenerator, games: Games): GameService =
            GameService(uuidGenerator, games)
}
