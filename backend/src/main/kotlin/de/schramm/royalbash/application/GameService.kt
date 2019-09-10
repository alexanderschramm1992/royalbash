package de.schramm.royalbash.application

import de.schramm.royalbash.api.ExternalModel
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Log
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.UUIDGenerator

class GameService(
        private val uuidGenerator: UUIDGenerator,
        private val games: Games
) {

    fun retrieveGame(gameId: String): ExternalModel.Game? {
        return retrieveDomainGame(gameId)?.toExternalModel()
    }

    fun retrieveGames(): List<ExternalModel.Game> {
        return games.findAll().map(Game::toExternalModel)
    }

    fun createGame(account1Id: String, account2Id: String): ExternalModel.Game {

        val player1Id = uuidGenerator.generateId()
        val player2Id = uuidGenerator.generateId()

        val game = Game(
                id = uuidGenerator.generateId(),
                player1 = Player(id = player1Id, name = account1Id),
                player2 = Player(id = player2Id, name = account2Id),
                log = Log())

        games.save(game)

        return game.toExternalModel()
    }

    fun commitGameEvent(gameId: String, gameEvent: GameEvent): ExternalModel.Game? {
        println("""Received event $gameEvent for game $gameId""")
        return retrieveDomainGame(gameId)
                ?.let(gameEvent::invoke)
                ?.toExternalModel()
    }

    fun retrieveGameIds(): List<String> {
        return games.findAll()
                .map { it.id }
    }

    private fun retrieveDomainGame(gameId: String): Game? {
        return games.findAll().firstOrNull { game -> gameId == game.id }
    }
}
