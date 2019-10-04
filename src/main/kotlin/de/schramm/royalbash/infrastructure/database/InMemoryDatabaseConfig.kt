package de.schramm.royalbash.infrastructure.database

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InMemoryDatabaseConfig {

    private val gamePersistenceOperations = InMemoryGamePersistenceOperations()

    @Bean
    fun gamePersistenceOperations(): GamePersistenceOperations = gamePersistenceOperations
}
