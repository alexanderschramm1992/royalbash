package de.schramm.royalbash.infrastructure.database

import java.util.*

class InMemoryGamePersistenceOperations: GamePersistenceOperations {

    private val games = mutableMapOf<String, GameDTO>()

    override fun findAll() = games.values.toList()

    override fun findById(id: String) = Optional.ofNullable(games[id])

    override fun save(game: GameDTO) {
        games[game.id] = game
    }

    override fun deleteById(id: String) {
        games.remove(id)
    }
}
