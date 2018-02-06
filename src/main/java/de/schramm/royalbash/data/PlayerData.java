package de.schramm.royalbash.data;

import de.schramm.royalbash.model.player.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerData {

    public static Set<Player> getPlayerSet() {

        return new HashSet<>(
                Arrays.asList(
                        Player.builder()
                                .id(UUID.fromString("934d46da-041e-4c02-8115-497c04eaaccf"))
                                .name("Player Blue")
                                .email("player.blue@royalbash.de")
                                .passwordHash("123qwe")
                                .build(),
                        Player.builder()
                                .id(UUID.fromString("74d41ee9-804a-4840-8c17-8ec4cd2ef16c"))
                                .name("Player Red")
                                .email("player.red@royalbash.de")
                                .passwordHash("qwe123")
                                .build()
                )
        );
    }
}
