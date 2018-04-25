package de.schramm.royalbash.model.player;

import de.schramm.royalbash.model.deck.Deck;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class Account {

    private UUID id;
    private String name;
    private String email;
    private String passwordHash;

    @Singular("deck")
    private Set<Deck> deckSet;
}
