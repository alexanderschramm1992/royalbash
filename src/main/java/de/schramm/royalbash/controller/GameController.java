package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.GameRequest;
import de.schramm.royalbash.controller.responsemodel.StateResponse;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.model.Game;
import de.schramm.royalbash.persistence.GameManager;
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
@RequestMapping("game")
public class GameController {

    private final GameManager gameManager;

    @Autowired
    public GameController(
            GameManager gameManager
    ) {
        this.gameManager = gameManager;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<StateResponse> drawCard(
            @RequestBody GameRequest requestParams
    ) {

        try {

            Game game = gameManager.findGame(requestParams.getGameId());

            log.info(game);
            return ResponseEntity.ok(StateResponse.fromGame(game));
        } catch (GameEngineException e) {

            log.warn("Failed to find game due to: " + e.getMessage());

            return ResponseEntity.badRequest().body(
                    StateResponse.builder()
                        .reason(e.getMessage())
                        .build()
            );
        }
    }
}
