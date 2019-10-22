package de.schramm.royalbash.infrastructure.database

import java.util.*

interface GamePersistenceOperations {

    fun findAll(): List<GameDTO>

    fun findById(id: String): Optional<GameDTO>

    fun save(game: GameDTO)

    fun deleteById(id: String)
}
