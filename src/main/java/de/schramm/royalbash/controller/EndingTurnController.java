package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.EndingTurnRequest;
import de.schramm.royalbash.controller.responsemodel.StateResponseGame;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.model.Game;
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
@RequestMapping("gameloop/endingturn")
public class EndingTurnController {

    private final GameManager gameManager;

    @Autowired
    private EndingTurnController(
            GameManager gameManager
    ) {
        this.gameManager = gameManager;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<StateResponseGame> endingTurn(
            @RequestBody EndingTurnRequest requestParams
    ) {

        try {

            Game game = gameManager.findGame(requestParams.getGameId());
            game.getBoard().endTurnOf(game.findPlayer(requestParams.getPlayerId()));
            gameManager.saveGame(game);

            return ResponseEntity.ok(StateResponseGame.fromGame(game));
        } catch (GameEngineException e) {

            log.warn("Failed to end turn due to: " + e.getMessage());

            return ResponseEntity.badRequest().body(
                    StateResponseGame.builder()
                            .reason(e.getMessage())
                            .build()
            );
        }
    }
}
