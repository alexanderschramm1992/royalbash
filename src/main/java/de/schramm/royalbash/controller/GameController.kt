package de.schramm.royalbash.controller

import de.schramm.royalbash.controller.ExternalModel.Game.Companion.toExternalModel
import de.schramm.royalbash.controller.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class GameController @Autowired
constructor(private val gameService: GameService) {

    @GetMapping("/game/{gameId}")
    internal fun retrieveGame(@PathVariable gameId: String): ExternalModel.Game {
        return gameService.retrieveGame(gameId)
                .map { toExternalModel(it) }
                .orElseThrow { GameNotFoundException(gameId) }
    }

    @PostMapping("/game")
    internal fun createGame(@RequestBody request: CreateGameRequest): ExternalModel.Game {
        return toExternalModel(gameService.createGame(request.accountId1, request.accountId2))
    }

    @PostMapping("/game/event")
    internal fun commitEvent(@RequestBody request: CommitGameEventRequest): ExternalModel.Game {
        return gameService.commitGameEvent(request.gameId, request.event)
                .map { toExternalModel(it) }
                .orElseThrow { GameNotFoundException(request.gameId) }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private inner class GameNotFoundException internal constructor(gameId: String) : RuntimeException(
            "No game for id $gameId")
}
