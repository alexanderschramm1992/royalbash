package de.schramm.royalbash.controller.responsemodel;

import de.schramm.royalbash.model.deck.Deck;
import de.schramm.royalbash.model.player.Player;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class PlayerExt {

    private UUID id;
    private String name;

    @Singular("deck")
    private Set<Deck> deckSet;

    public static PlayerExt fromPlayer(Player player) {

        return PlayerExt.builder()
                .id(player.getId())
                .name(player.getName())
                .deckSet(player.getDeckSet())
                .build();
    }
}
