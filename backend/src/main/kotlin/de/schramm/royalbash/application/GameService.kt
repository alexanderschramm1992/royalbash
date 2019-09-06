package de.schramm.royalbash.application

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Log
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.UUIDGenerator
import java.util.*

class GameService(
        private val uuidGenerator: UUIDGenerator,
        private val games: Games
) {

    fun retrieveGame(gameId: String): Game? {
        return games.findAll().firstOrNull { game -> gameId == game.id }
    }

    fun createGame(account1Id: String, account2Id: String): Game {

        val player1Id = uuidGenerator.generateId()
        val player2Id = uuidGenerator.generateId()

        val game = Game(
                id = uuidGenerator.generateId(),
                player1 = Player(id = player1Id, name = account1Id),
                player2 = Player(id = player2Id, name = account2Id),
                log = Log())

        games.save(game)

        return game
    }

    fun commitGameEvent(gameId: String, gameEvent: GameEvent): Game? {
        println("""Received event $gameEvent for game $gameId""")
        return retrieveGame(gameId)?.let(gameEvent::invoke)
    }

    fun retrieveGameIds(): List<String> {
        return games.findAll()
                .map { it.id }
    }
}
