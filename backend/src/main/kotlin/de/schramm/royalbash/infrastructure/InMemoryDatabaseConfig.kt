package de.schramm.royalbash.infrastructure

import de.schramm.royalbash.infrastructure.database.GamePersistenceOperations
import de.schramm.royalbash.infrastructure.database.InMemoryGamePersistenceOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InMemoryDatabaseConfig {

    private val gamePersistenceOperations = InMemoryGamePersistenceOperations()

    @Bean
    fun gamePersistenceOperations(): GamePersistenceOperations = gamePersistenceOperations
}
