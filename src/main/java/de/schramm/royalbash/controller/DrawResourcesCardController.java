package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.DrawRequest;
import de.schramm.royalbash.controller.responsemodel.StateResponseGame;
import de.schramm.royalbash.core.exception.GameEngineException;
import de.schramm.royalbash.core.domain.game.Game;
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
@RequestMapping("gameloop/drawResourcesCard")
class DrawResourcesCardController {

    private final GameManager gameManager;

    @Autowired
    private DrawResourcesCardController(
            GameManager gameManager
    ) {
        this.gameManager = gameManager;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<StateResponseGame> draw(
            @RequestBody DrawRequest requestParams
    ) {

        try {

            Game game = gameManager.findGame(requestParams.getGameId());
            game.findPlayer(requestParams.getPlayerId()).drawResourcesCard();
            gameManager.saveGame(game);
            return ResponseEntity.ok(StateResponseGame.fromGame(game));
        } catch (GameEngineException e) {

            log.warn("Failed to draw Resources Card due to: " + e.getMessage());

            return ResponseEntity.badRequest().body(
                    StateResponseGame.builder()
                            .reason(e.getMessage())
                            .build()
            );
        }
    }
}
