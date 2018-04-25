package de.schramm.royalbash.data;

import de.schramm.royalbash.model.deck.Deck;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static de.schramm.royalbash.data.CardData.*;

public class DeckData {

    public static Set<Deck> getDeckSet() {

        return new HashSet<>(
                Arrays.asList(
                    Deck.builder()
                        .id(UUID.fromString("c31a66c7-2f76-4e81-a922-835238533967"))
                        .card(YOUTHFUL_KNIGHT)
                        .card(IRON_MACE)
                        .build(),
                    Deck.builder()
                        .id(UUID.fromString("c31a66c7-2f76-5f92-a922-835238533967"))
                        .card(VETERAN_KNIGHT)
                        .card(OAKWOOD_SHIELD)
                        .build()
                )
        );
    }
}
