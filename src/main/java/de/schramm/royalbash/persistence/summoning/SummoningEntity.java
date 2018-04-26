package de.schramm.royalbash.persistence.summoning;

import de.schramm.royalbash.model.Summoning;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class SummoningEntity {

    private final UUID id;
    private final UUID card;

    private int currentCost;
    private int currentHealth;
    private int currentStrength;

    public static SummoningEntity toEntity(Summoning summoning) {

        return SummoningEntity.builder()
                .id(summoning.getId())
                .card(summoning.getCard().getId())
                .currentHealth(summoning.getCurrentHealth())
                .currentStrength(summoning.getCurrentStrength())
                .build();
    }
}
