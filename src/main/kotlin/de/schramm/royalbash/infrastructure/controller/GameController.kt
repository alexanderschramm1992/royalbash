package de.schramm.royalbash.infrastructure.controller

import de.schramm.royalbash.api.ExternalModel
import de.schramm.royalbash.application.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class GameController @Autowired
constructor(private val gameService: GameService) {

    @GetMapping("/game/{gameId}")
    fun retrieveGame(@PathVariable gameId: String): ExternalModel.Game {
        return gameService.retrieveGame(gameId)
               ?: throw GameNotFoundException(gameId)
    }

    @GetMapping("/game/id")
    fun retrieveGameIds(): Array<String> {
        return gameService.retrieveGameIds().toTypedArray()
    }

    @GetMapping("/game")
    fun retrieveGames(): Array<ExternalModel.Game> {
        return gameService.retrieveGames().toTypedArray()
    }

    @PostMapping("/game")
    fun createGame(@RequestBody request: CreateGameRequest): ExternalModel.Game {
        return gameService.createGame(request.accountId1, request.accountId2)
    }

    @PostMapping("/game/{gameId}/event")
    fun commitEvent(
            @PathVariable gameId: String,
            @RequestBody request: CommitGameEventRequest): ExternalModel.Game {
        return gameService.commitGameEvent(gameId, request.event)
               ?: throw GameNotFoundException(gameId)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private inner class GameNotFoundException constructor(gameId: String): RuntimeException(
            "No game for id $gameId")
}
