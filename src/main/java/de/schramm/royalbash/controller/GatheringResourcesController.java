package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.GatheringResourcesRequest;
import de.schramm.royalbash.controller.responsemodel.StateResponse;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.model.card.EffectContext;
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
public class GatheringResourcesController {

    private final GameManager gameManager;

    @Autowired
    private GatheringResourcesController(
            GameManager gameManager
    ) {
        this.gameManager = gameManager;
    }

    @RequestMapping(
            value = "gameloop/gatheringResources",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<StateResponse> gatherResources(
            @RequestBody GatheringResourcesRequest requestParams
    ) {

        try {

            val game = gameManager.findGame(requestParams.getGameId());
            val player = game.findPlayer(requestParams.getPlayerId());
            val resourcesCard = player.getHand().findResourcesCard(requestParams.getCardId());
            resourcesCard.getPlayEffect().apply(
                    EffectContext.builder()
                            .game(game)
                            .owner(player)
                            .build()
            );
            gameManager.saveGame(game);
            return ResponseEntity.ok(StateResponse.fromGame(game));
        } catch (GameEngineException e) {

            log.warn("Failed to gather resources due to: " + e.getMessage());
            return ResponseEntity.badRequest().body(StateResponse.fromError(e.getMessage()));
        }
    }
}
