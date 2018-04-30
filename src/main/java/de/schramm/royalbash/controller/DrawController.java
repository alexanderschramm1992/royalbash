package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.DrawRequest;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.handler.DrawHandler;
import de.schramm.royalbash.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gameloop/draw")
public class DrawController {

    private final DrawHandler drawHandler;

    @Autowired
    public DrawController(DrawHandler drawHandler) {
        this.drawHandler = drawHandler;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Card> drawCard(
            @RequestBody DrawRequest requestParams
    ) {

        try {

            Card card = drawHandler.drawCard(
                    requestParams.getPlayerId()
            );

            return ResponseEntity.ok(card);
        } catch (GameEngineException e) {

            System.err.println("Failed to draw card due to: " + e.getMessage());

            return ResponseEntity.badRequest().build();
        }
    }
}
