package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Builder
@Getter
@ToString
public class Turn {

    private int counter;
    private UUID playerId;

    public void increaseTurnCounter() {

        counter++;
    }

    public void swapPlayer(Board board) {

        if (playerId.equals(board.getPlayerBlue().getId())) {

            playerId = board.getPlayerRed().getId();
        } else if (playerId.equals(board.getPlayerRed().getId())) {

            playerId = board.getPlayerBlue().getId();
        } else {

            // ToDo: Handle error
        }
    }
}
