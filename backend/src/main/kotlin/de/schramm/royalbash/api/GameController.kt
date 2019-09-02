package de.schramm.royalbash.api

import de.schramm.royalbash.application.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class GameController @Autowired
constructor(private val gameService: GameService) {

    @GetMapping("/game/{gameId}")
    internal fun retrieveGame(@PathVariable gameId: String): ExternalModel.Game {
        return gameService.retrieveGame(gameId)
                .map { it.toExternalModel() }
                .orElseThrow { GameNotFoundException(gameId) }
    }

    @GetMapping("/game/id")
    internal fun retrieveGameIds(): Array<String> {
        return gameService.retrieveGameIds().toTypedArray()
    }

    @PostMapping("/game")
    internal fun createGame(@RequestBody request: CreateGameRequest): ExternalModel.Game {
        return gameService.createGame(request.accountId1, request.accountId2).toExternalModel()
    }

    @PostMapping("/game/{gameId}/event")
    internal fun commitEvent(
            @PathVariable gameId: String,
            @RequestBody request: CommitGameEventRequest): ExternalModel.Game {
        return gameService.commitGameEvent(gameId, request.event)
                .map { it.toExternalModel() }
                .orElseThrow { GameNotFoundException(gameId) }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private inner class GameNotFoundException internal constructor(gameId: String) : RuntimeException(
            "No game for id $gameId")
}
