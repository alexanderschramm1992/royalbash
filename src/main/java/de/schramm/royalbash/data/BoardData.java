package de.schramm.royalbash.data;

import de.schramm.royalbash.core.domain.game.board.Board;
import de.schramm.royalbash.core.domain.game.board.Turn;

import java.util.*;

import static de.schramm.royalbash.data.PlayerData.PLAYER_1;
import static de.schramm.royalbash.data.PlayerData.PLAYER_2;

class BoardData {

    static final Board BOARD = Board.builder()
            .id(UUID.fromString("b030c982-ed9b-4fc4-89b0-377ecf5d228d"))
            .turn(
                    Turn.builder()
                            .counter(1)
                            .currentTurnPlayerId(PLAYER_1.getId())
                            .playerBlueId(PLAYER_1.getId())
                            .playerRedId(PLAYER_2.getId())
                            .build()
            )
            .playerBlue(PLAYER_1)
            .playerRed(PLAYER_2)
            .build();
}
