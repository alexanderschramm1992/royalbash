package de.schramm.royalbash.controller;

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
            value = "gameloop/summoninstance",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Summoning> summonInstance(
            @RequestBody UUID boardId,
            @RequestBody UUID playerInstanceId,
            @RequestBody UUID cardId
    ) {

        try {

            return ResponseEntity.ok(
                summonHandler.summon(
                    boardId,
                    playerInstanceId,
                    cardId
                )
            );
        } catch (GameEngineException exception) {

            return ResponseEntity.badRequest().build();
        }
    }
}
