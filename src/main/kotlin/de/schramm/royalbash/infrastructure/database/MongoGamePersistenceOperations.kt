package de.schramm.royalbash.infrastructure.database

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoGamePersistenceOperations:
        MongoRepository<GameDTO, String>,
        GamePersistenceOperations
