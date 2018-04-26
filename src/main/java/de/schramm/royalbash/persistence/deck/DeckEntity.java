package de.schramm.royalbash.persistence.deck;

import de.schramm.royalbash.model.Card;
import de.schramm.royalbash.model.Deck;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
@Builder
public class DeckEntity {

    private UUID id;
    private List<UUID> cards;

    public static DeckEntity toEntity(Deck deck) {

        return DeckEntity.builder()
                .id(deck.getId())
                .cards(deck.getCards().stream()
                        .map(Card::getId)
                        .collect(Collectors.toList())
                ).build();
    }
}
