package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        return toExternalModel(gameService.retrieveGame(gameId));
    }

    @PostMapping("/game")
    ExternalModel.Game createGame(@RequestBody CreateGameRequest request) {
        return toExternalModel(gameService.createGame(request.getAccountId1(), request.getAccountId2()));
    }

    @PostMapping("/game/event")
    ExternalModel.Game commitEvent(@RequestBody CommitGameEventRequest request) {
        return toExternalModel(gameService.commitGameEvent(request.getGameId(), request.getEvent()));
    }
}
