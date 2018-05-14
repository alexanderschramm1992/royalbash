package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.DrawRequest;
import de.schramm.royalbash.controller.responsemodel.StateResponse;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.handler.DrawHandler;
import de.schramm.royalbash.model.Game;
import de.schramm.royalbash.persistence.game.GameManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("gameloop/drawSummoningCard")
public class DrawSummoningCardController {

    private final GameManager gameManager;
    private final DrawHandler drawSummoningCardHandler;

    @Autowired
    public DrawSummoningCardController(
            GameManager gameManager,
            DrawHandler drawSummoningCardHandler
    ) {
        this.gameManager = gameManager;
        this.drawSummoningCardHandler = drawSummoningCardHandler;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<StateResponse> draw(
            @RequestBody DrawRequest requestParams
    ) {

        try {

            Game game = gameManager.findGame(requestParams.getGameId());
            game = drawSummoningCardHandler.draw(
                    game,
                    game.findPlayer(requestParams.getPlayerId())
            );

            return ResponseEntity.ok(StateResponse.fromGame(game));
        } catch (GameEngineException e) {

            log.warn("Failed to draw Summoning Card due to: " + e.getMessage());

            return ResponseEntity.badRequest().body(
                    StateResponse.builder()
                            .reason(e.getMessage())
                            .build()
            );
        }
    }
}
