package de.schramm.royalbash.model;

import de.schramm.royalbash.model.summoningcard.SummoningCard;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class Equipment {

    private final UUID id;
    private final SummoningCard weapon;

    private int currentCost;
    private int currentHealth;
    private int currentStrength;
}
