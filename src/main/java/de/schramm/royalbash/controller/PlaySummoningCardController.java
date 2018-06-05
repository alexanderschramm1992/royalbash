package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.SummonRequest;
import de.schramm.royalbash.controller.responsemodel.StateResponse;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.persistence.GameManager;
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

    @Autowired
    private PlaySummoningCardController(
            GameManager gameManager
    ) {
        this.gameManager = gameManager;
    }

    @RequestMapping(
            value = "gameloop/summon",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<StateResponse> summonInstance(
            @RequestBody SummonRequest requestParams
    ) {

        try {

            val game = gameManager.findGame(requestParams.getGameId());
            val player = game.findPlayer(requestParams.getPlayerId());
            player.playSummoningCard(
                    player.getHand().findSummoningCard(requestParams.getCardId()),
                    game.findTarget(requestParams.getTargetId())
            );
            gameManager.saveGame(game);
            return ResponseEntity.ok(StateResponse.fromGame(game));
        } catch (GameEngineException e) {

            log.warn("Failed to summon card due to: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
