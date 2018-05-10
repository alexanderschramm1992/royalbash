package de.schramm.royalbash.gameengine.handler;

import de.schramm.royalbash.model.Game;
import de.schramm.royalbash.model.Turn;
import org.springframework.stereotype.Component;

@Component
public class TurnHandler {

    public Game endTurn(Game game) {

        Turn turn = game.getBoard().getTurn();

        turn.increaseTurnCounter();
        turn.swapPlayer(game.getBoard());

        return game;
    }
}
