package de.schramm.royalbash.infrastructure.database

import de.schramm.royalbash.application.Games
import de.schramm.royalbash.domain.Game

class GamePersistenceMapper(private val gamePersistenceOperations: GamePersistenceOperations): Games {

    override fun findAll() = gamePersistenceOperations.findAll().map { it.toGame() }

    override fun findById(id: String) =
            gamePersistenceOperations.findById(id)
                    .map { it.toGame() }
                    .orElse(null)!!

    override fun save(game: Game) {
        gamePersistenceOperations.save(game.toDTO())
    }

    override fun deleteById(id: String) {
        gamePersistenceOperations.deleteById(id)
    }
}
