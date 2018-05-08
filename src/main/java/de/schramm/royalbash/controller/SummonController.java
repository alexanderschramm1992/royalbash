package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.SummonRequest;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.handler.SummonHandler;
import de.schramm.royalbash.model.Summoning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SummonController {

    private final SummonHandler summonHandler;

    @Autowired
    public SummonController(SummonHandler summonHandler) {
        this.summonHandler = summonHandler;
    }

    @RequestMapping(
            value = "gameloop/summon",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Summoning> summonInstance(
            @RequestBody SummonRequest requestParams
            ) {

        try {

            return ResponseEntity.ok(
                summonHandler.summon(
                    requestParams.getGameId(),
                    requestParams.getPlayerId(),
                    requestParams.getCardId(),
                    requestParams.getTargetId()
                )
            );
        } catch (GameEngineException e) {

            System.err.println("Failed to summon card due to: " + e.getMessage());

            return ResponseEntity.badRequest().build();
        }
    }
}
