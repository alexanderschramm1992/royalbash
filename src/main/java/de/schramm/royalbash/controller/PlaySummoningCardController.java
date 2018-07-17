package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.SummonRequest;
import de.schramm.royalbash.controller.responsemodel.StateResponseGame;
import de.schramm.royalbash.core.exception.GameEngineException;
import de.schramm.royalbash.persistence.GameManager;
import de.schramm.royalbash.util.UUIDGenerator;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
class PlaySummoningCardController {

    private final GameManager gameManager;
    private final UUIDGenerator uuidGenerator;

    @Autowired
    private PlaySummoningCardController(
            GameManager gameManager,
            UUIDGenerator uuidGenerator
    ) {
        this.gameManager = gameManager;
        this.uuidGenerator = uuidGenerator;
    }

    @RequestMapping(
            value = "gameloop/summon",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<StateResponseGame> summonInstance(
            @RequestBody SummonRequest requestParams
    ) {

        try {

            val game = gameManager.findGame(requestParams.getGameId());
            val player = game.findPlayer(requestParams.getPlayerId());
            player.playSummoningCard(
                    player.getHand().findSummoningCard(requestParams.getCardId()),
                    game.findTarget(requestParams.getTargetId()),
                    uuidGenerator.generateUUID()
            );
            gameManager.saveGame(game);
            return ResponseEntity.ok(StateResponseGame.fromGame(game));
        } catch (GameEngineException e) {

            log.warn("Failed to summon card due to: " + e.getMessage());
            return ResponseEntity.badRequest().body(StateResponseGame.fromError(e.getMessage()));
        }
    }
}
