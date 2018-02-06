package de.schramm.royalbash.controller;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.handler.GameHandler;
import de.schramm.royalbash.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GameController {

    private final GameHandler gameHandler;

    @Autowired
    public GameController(
            GameHandler gameHandler
    ) {

        this.gameHandler = gameHandler;
    }

    @RequestMapping(
            value = "gameloop/game/start",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Game> startGame(
            @RequestBody UUID playerBlueId,
            @RequestBody UUID playerRedId,
            @RequestBody UUID playerBlueDeckId,
            @RequestBody UUID playerRedDeckId
    ) {

        try {

            Game game = gameHandler.startGame(
                    playerBlueId,
                    playerRedId,
                    playerBlueDeckId,
                    playerRedDeckId
            );

            return ResponseEntity.ok(game);
        } catch (GameEngineException e) {

            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(
            value = "gameloop/game/end",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity endGame(
            @RequestBody UUID gameId
    ) {

        try {

            gameHandler.endGame(gameId);

            return ResponseEntity.ok().build();
        } catch (DomainObjectDoesNotExistException e) {

            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(
            value = "gameloop/game",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Game> getGame(
            @RequestBody UUID gameId
    ) {

        try {

            Game game = gameHandler.getGame(gameId);

            return ResponseEntity.ok(game);
        } catch (DomainObjectDoesNotExistException e) {

            return ResponseEntity.badRequest().build();
        }
    }
}
