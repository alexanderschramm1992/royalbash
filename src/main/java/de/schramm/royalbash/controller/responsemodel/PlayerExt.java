package de.schramm.royalbash.controller.responsemodel;

import de.schramm.royalbash.model.deck.Deck;
import de.schramm.royalbash.model.player.Account;
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

    public static PlayerExt fromPlayer(Account account) {

        return PlayerExt.builder()
                .id(account.getId())
                .name(account.getName())
                .deckSet(account.getDeckSet())
                .build();
    }
}
