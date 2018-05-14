package de.schramm.royalbash.data;

import de.schramm.royalbash.model.Game;

import java.util.UUID;

import static de.schramm.royalbash.data.AccountData.ACCOUNT_1;
import static de.schramm.royalbash.data.AccountData.ACCOUNT_2;
import static de.schramm.royalbash.data.BoardData.BOARD;

public class GameData {

    public static final Game GAME = Game.builder()
            .id(UUID.fromString("6d5864f4-5fb1-4615-bf6a-07a1211ef6d3"))
            .accountBlue(ACCOUNT_1.getId())
            .accountRed(ACCOUNT_2.getId())
            .board(BOARD)
            .build();
}
