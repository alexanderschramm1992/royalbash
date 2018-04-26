package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Turn {

    private int counter;
    private Player player;

    public void increaseTurnCounter() {

        counter++;
    }

    public void swapPlayer(Board board) {

        if (player.equals(board.getPlayerBlue())) {

            player = board.getPlayerRed();
        } else if (player.equals(board.getPlayerRed())) {

            player = board.getPlayerBlue();
        } else {

            // ToDo: Handle error
        }
    }
}
