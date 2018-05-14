package de.schramm.royalbash.data;

import de.schramm.royalbash.model.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static de.schramm.royalbash.data.AccountData.ACCOUNT_1;
import static de.schramm.royalbash.data.AccountData.ACCOUNT_2;
import static de.schramm.royalbash.data.DeckData.DECK_1;
import static de.schramm.royalbash.data.DeckData.DECK_2;
import static de.schramm.royalbash.data.HandData.HAND_1;
import static de.schramm.royalbash.data.HandData.HAND_2;

public class PlayerData {

    public static final Player PLAYER_1 = Player.builder()
            .id(UUID.fromString("8dbc6953-e25e-49f0-a298-7a0ea721de6c"))
            .health(20)
            .accountId(ACCOUNT_1.getId())
            .deck(DECK_1)
            .hand(HAND_1)
            .build();

    public static final Player PLAYER_2 = Player.builder()
            .id(UUID.fromString("736cb270-c73c-4257-b49f-d71d9b4cb59b"))
            .health(20)
            .accountId(ACCOUNT_2.getId())
            .deck(DECK_2)
            .hand(HAND_2)
            .build();
}
