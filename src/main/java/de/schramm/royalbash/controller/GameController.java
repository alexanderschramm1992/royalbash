package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.service.GameService;
import de.schramm.royalbash.controller.service.core.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/game/{gameId}")
    Game retrieveGame(String gameId) {
        return gameService.retrieveGame(gameId);
    }
}
