package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static de.schramm.royalbash.controller.ExternalModel.Game.*;

@RestController
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/game/{gameId}")
    ExternalModel.Game retrieveGame(String gameId) {
        return gameService.retrieveGame(gameId)
            .map(ExternalModel.Game::toExternalModel)
            .orElseThrow(() -> new GameNotFoundException(gameId));
    }

    @PostMapping("/game")
    ExternalModel.Game createGame(@RequestBody CreateGameRequest request) {
        return toExternalModel(gameService.createGame(request.getAccountId1(), request.getAccountId2()));
    }

    @PostMapping("/game/event")
    ExternalModel.Game commitEvent(@RequestBody CommitGameEventRequest request) {
        return toExternalModel(gameService.commitGameEvent(request.getGameId(), request.getEvent()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class GameNotFoundException extends RuntimeException {
        GameNotFoundException(String gameId) {
            super("No game for id " + gameId);
        }
    }
}
