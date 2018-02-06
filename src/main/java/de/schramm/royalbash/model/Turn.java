package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class Turn {

    private int turnCounter;
    private UUID playerInstanceId;

    public void increaseTurnCounter() {

        turnCounter++;
    }

    public void swapPlayerInstance(Board board) {

        if (playerInstanceId.equals(board.getPlayerBlueInstance().getId())) {

            playerInstanceId = board.getPlayerRedInstance().getId();
        } else if (playerInstanceId.equals(board.getPlayerRedInstance().getId())) {

            playerInstanceId = board.getPlayerBlueInstance().getId();
        }
    }
}
