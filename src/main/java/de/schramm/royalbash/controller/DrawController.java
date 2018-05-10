package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.DrawRequest;
import de.schramm.royalbash.controller.responsemodel.StateResponse;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.handler.DrawHandler;
import de.schramm.royalbash.model.Card;
import de.schramm.royalbash.model.Game;
import de.schramm.royalbash.persistence.game.GameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gameloop/draw")
public class DrawController {

    private final GameManager gameManager;
    private final DrawHandler drawHandler;

    @Autowired
    public DrawController(
            GameManager gameManager,
            DrawHandler drawHandler
    ) {
        this.gameManager = gameManager;
        this.drawHandler = drawHandler;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<StateResponse> drawCard(
            @RequestBody DrawRequest requestParams
    ) {

        try {

            Game game = gameManager.findGame(requestParams.getGameId());
            game = drawHandler.drawCard(
                    game,
                    game.findPlayer(requestParams.getPlayerId())
            );

            return ResponseEntity.ok(StateResponse.fromGame(game));
        } catch (GameEngineException e) {

            System.err.println("Failed to draw card due to: " + e.getMessage());

            return ResponseEntity.badRequest().build();
        }
    }
}
