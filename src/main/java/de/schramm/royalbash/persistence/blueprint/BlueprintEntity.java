package de.schramm.royalbash.persistence.blueprint;

import de.schramm.royalbash.model.Card;
import de.schramm.royalbash.model.Blueprint;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
@Builder
public class BlueprintEntity {

    private UUID id;
    private List<UUID> cards;

    public static BlueprintEntity toEntity(Blueprint blueprint) {

        return BlueprintEntity.builder()
                .id(blueprint.getId())
                .cards(blueprint.getCards().stream()
                        .map(Card::getId)
                        .collect(Collectors.toList())
                ).build();
    }
}
