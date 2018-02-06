package de.schramm.royalbash.persistence.deck;

import de.schramm.royalbash.model.deck.Deck;
import de.schramm.royalbash.model.card.Card;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
@Builder
public class DeckEntity {

    private UUID id;
    private List<UUID> deckList;

    public static DeckEntity toEntity(Deck deck) {

        return DeckEntity.builder()
                .id(deck.getId())
                .deckList(deck.getCardList().stream()
                        .map(Card::getId)
                        .collect(Collectors.toList())
                ).build();
    }
}
