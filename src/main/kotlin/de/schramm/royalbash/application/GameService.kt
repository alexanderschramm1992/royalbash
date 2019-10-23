package de.schramm.royalbash.application

import de.schramm.royalbash.api.ExternalModel
import de.schramm.royalbash.application.gameevent.GameEventDTO
import de.schramm.royalbash.domain.*

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

        val player1Id = uuidGenerator.id()
        val player2Id = uuidGenerator.id()

        val game = Game(
                id = uuidGenerator.id(),
                player1 = Player(id = player1Id, name = account1Id, spots = fillSpots(uuidGenerator)),
                player2 = Player(id = player2Id, name = account2Id, spots = fillSpots(uuidGenerator)))
                .let {
                    it.copy(logs = listOf(Log(uuidGenerator.id(),
                                              "Started Match between ${it.player1.name} and ${it.player2.name}"))) }

        games.save(game)

        return game.toExternalModel()
    }

    fun commitGameEvent(gameId: String, gameEvent: GameEventDTO): ExternalModel.Game? {
        return retrieveDomainGame(gameId)
                ?.let { gameEvent.invoke(it, uuidGenerator) }
                ?.also(games::save)
                ?.toExternalModel()
    }

    fun retrieveGameIds(): List<String> {
        return games.findAll()
                .map { it.id }
    }

    private fun retrieveDomainGame(gameId: String): Game? {
        return games.findAll().firstOrNull { game -> gameId == game.id }
    }

    private fun fillSpots(uuidGenerator: UUIDGenerator,
                          spots: List<Spot> = emptyList()): List<Spot> {
        val numberOfSpotsMissing = NUMBER_OF_SPOTS - spots.size
        return spots + (0 until numberOfSpotsMissing).map { Spot(uuidGenerator.id()) }
    }
}
