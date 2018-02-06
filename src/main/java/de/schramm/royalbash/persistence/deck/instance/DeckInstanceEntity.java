package de.schramm.royalbash.persistence.deck.instance;

import de.schramm.royalbash.model.card.instance.CardInstance;
import de.schramm.royalbash.model.deck.DeckInstance;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
@Builder
public class DeckInstanceEntity {

    private UUID id;
    private List<UUID> cardInstanceList;

    public static DeckInstanceEntity toEntity(DeckInstance deckInstance) {

        return DeckInstanceEntity.builder()
                .id(deckInstance.getId())
                .cardInstanceList(deckInstance.getCardInstanceList().stream()
                        .map(CardInstance::getId)
                        .collect(Collectors.toList())
                ).build();
    }
}
