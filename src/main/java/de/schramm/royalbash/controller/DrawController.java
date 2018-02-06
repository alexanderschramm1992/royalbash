package de.schramm.royalbash.controller;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.handler.DrawHandler;
import de.schramm.royalbash.model.card.instance.CardInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DrawController {

    private final DrawHandler drawHandler;

    @Autowired
    public DrawController(DrawHandler drawHandler) {
        this.drawHandler = drawHandler;
    }

    @RequestMapping(
            value = "gameloop/draw",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<CardInstance> drawCard(
            @RequestBody UUID playerInstanceId,
            @RequestBody UUID deckInstanceId,
            @RequestBody UUID boardId
    ) {

        try {

            CardInstance cardInstance = drawHandler.drawCardInstance(
                    playerInstanceId,
                    deckInstanceId,
                    boardId
            );

            return ResponseEntity.ok(cardInstance);
        } catch (GameEngineException e) {

            return ResponseEntity.badRequest().build();
        }
    }
}
