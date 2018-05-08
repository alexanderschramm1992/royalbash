package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.responsemodel.StateResponse;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.handler.AttackHandler;
import de.schramm.royalbash.gameengine.handler.TurnHandler;
import de.schramm.royalbash.model.Game;
import de.schramm.royalbash.model.Turn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class BoardController {

    private final AttackHandler attackHandler;
    private final TurnHandler turnHandler;

    @Autowired
    public BoardController(
            AttackHandler attackHandler,
            TurnHandler turnHandler
    ) {

        this.attackHandler = attackHandler;
        this.turnHandler = turnHandler;
    }

    @RequestMapping(
            value = "gameloop/turn",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Turn> getTurn(
            @RequestBody UUID boardId
    ) {

        try {

            Turn turn = turnHandler.getTurn(boardId);

            return ResponseEntity.ok(turn);
        } catch (GameEngineException exception) {

            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(
            value = "gameloop/turn/end",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Boolean> endTurn(
            @RequestBody UUID boardId
    ) {

        try {

            turnHandler.endTurn(boardId);

            return ResponseEntity.ok().build();
        } catch (GameEngineException exception) {

            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(
            value = "gameloop/attackinstance",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<StateResponse> attackInstance(
            @RequestBody UUID boardId,
            @RequestBody UUID attackingInstanceId,
            @RequestBody UUID attackedInstanceId
    ) {

        try {

            Game game = attackHandler.attackSummoning(
                    boardId,
                    attackingInstanceId,
                    attackedInstanceId
            );

            return ResponseEntity.ok(StateResponse.fromGame(game));
        } catch (GameEngineException exception) {

            return ResponseEntity
                    .status(400)
                    .body(StateResponse.fromError(exception.getMessage()));
        }
    }
}
