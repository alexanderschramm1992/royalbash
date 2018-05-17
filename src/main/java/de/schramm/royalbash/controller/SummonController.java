package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.SummonRequest;
import de.schramm.royalbash.controller.responsemodel.StateResponse;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.handler.SummonHandler;
import de.schramm.royalbash.model.Game;
import de.schramm.royalbash.persistence.GameManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class SummonController {

    private final GameManager gameManager;
    private final SummonHandler summonHandler;

    @Autowired
    public SummonController(
            GameManager gameManager,
            SummonHandler summonHandler
    ) {
        this.gameManager = gameManager;
        this.summonHandler = summonHandler;
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

        log.info("Summon endpoint called");
        try {

            Game game = gameManager.findGame(requestParams.getGameId());
            game = summonHandler.summon(
                    game,
                    game.findPlayer(requestParams.getPlayerId()),
                    game.findHandCard(requestParams.getCardId()),
                    game.findTarget(requestParams.getTargetId())
            );

            return ResponseEntity.ok(StateResponse.fromGame(game));
        } catch (GameEngineException e) {

            System.err.println("Failed to summon card due to: " + e.getMessage());

            return ResponseEntity.badRequest().build();
        }
    }
}
