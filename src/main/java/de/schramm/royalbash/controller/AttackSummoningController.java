package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.AttackSummoningRequest;
import de.schramm.royalbash.controller.responsemodel.StateResponse;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.model.card.EffectContext;
import de.schramm.royalbash.persistence.GameManager;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("gameloop/attackSummoning")
public class AttackSummoningController {

    private final GameManager gameManager;

    @Autowired
    private AttackSummoningController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StateResponse> attack(@RequestBody AttackSummoningRequest requestParams) {

        try {

            val game = gameManager.findGame(requestParams.getGameId());
            val attackingSummoning = game.findSummoning(requestParams.getAttackingSummoningId());
            val attackedSummoning = game.findSummoning(requestParams.getAttackedSummoningId());
            attackingSummoning.getAttackSummoningEffect().apply(
                    attackingSummoning,
                    attackedSummoning,
                    EffectContext.builder()
                            .game(game)
                            .owner(game.findPlayer(requestParams.getPlayerId()))
                            .build()
            );
            gameManager.saveGame(game);
            return ResponseEntity.ok(StateResponse.fromGame(game));
        } catch (GameEngineException e) {

            log.warn("Failed to attack Summoning due to: " + e.getMessage());
            return ResponseEntity.badRequest().body(StateResponse.fromError(e.getMessage()));
        }
    }
}
