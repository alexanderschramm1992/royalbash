package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.AttackingTargetRequest;
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
@RequestMapping("gameloop/attackingTarget")
public class AttackingTargetController {

    private final GameManager gameManager;

    @Autowired
    private AttackingTargetController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StateResponse> attack(@RequestBody AttackingTargetRequest requestParams) {

        try {

            val game = gameManager.findGame(requestParams.getGameId());
            val player = game.findPlayer(requestParams.getPlayerId());
            val enemyPlayer = gameManager.findEnemyPlayer(requestParams.getPlayerId(), game);
            val attackingSummoning = player.findSummoning(requestParams.getAttackingSummoningId());
            val attackedTarget = enemyPlayer.findTarget(requestParams.getAttackedTargetId());
            attackingSummoning.getAttackingTargetEffect().apply(
                    attackingSummoning,
                    attackedTarget,
                    EffectContext.builder()
                            .game(game)
                            .owner(game.findPlayer(requestParams.getPlayerId()))
                            .build()
            );
            gameManager.saveGame(game);
            return ResponseEntity.ok(StateResponse.fromGame(game));
        } catch (GameEngineException e) {

            log.warn("Failed to attack Target due to: " + e.getMessage());
            return ResponseEntity.badRequest().body(StateResponse.fromError(e.getMessage()));
        }
    }
}
