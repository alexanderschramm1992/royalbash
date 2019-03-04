package de.schramm.royalbash.infrastructure

import de.schramm.royalbash.infrastructure.database.GamePersistenceOperations
import de.schramm.royalbash.infrastructure.database.InMemoryGamePersistenceOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("inmemory")
@Configuration
open class InMemoryDatabaseConfig {

    private val gamePersistenceOperations = InMemoryGamePersistenceOperations()

    @Bean
    open fun gamePersistenceOperations(): GamePersistenceOperations = gamePersistenceOperations
}
