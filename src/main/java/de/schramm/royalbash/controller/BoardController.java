package de.schramm.royalbash.controller;

import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.handler.AttackHandler;
import de.schramm.royalbash.gameengine.handler.SummonHandler;
import de.schramm.royalbash.gameengine.handler.TurnHandler;
import de.schramm.royalbash.model.Board;
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

    private final SummonHandler summonHandler;

    private final AttackHandler attackHandler;

    private final TurnHandler turnHandler;

    @Autowired
    public BoardController(
            SummonHandler summonHandler,
            AttackHandler attackHandler,
            TurnHandler turnHandler
    ) {

        this.summonHandler = summonHandler;
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
    public ResponseEntity endTurn(
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
            value = "gameloop/summoninstance",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Board> summonInstance(
            @RequestBody UUID boardId,
            @RequestBody UUID playerInstanceId,
            @RequestBody UUID cardId
    ) {

        try {

            Board board = summonHandler.summon(
                    boardId,
                    playerInstanceId,
                    cardId
            );

            return ResponseEntity.ok(board);
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
    public ResponseEntity<Board> attackInstance(
            @RequestBody UUID boardId,
            @RequestBody UUID attackingInstanceId,
            @RequestBody UUID attackedInstanceId
    ) {

        try {

            Board board = attackHandler.attackCardInstance(
                    boardId,
                    attackingInstanceId,
                    attackedInstanceId
            );

            return ResponseEntity.ok(board);
        } catch (GameEngineException exception) {

            return ResponseEntity.badRequest().build();
        }
    }
}
