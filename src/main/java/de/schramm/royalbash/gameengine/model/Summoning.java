package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.model.summoningcard.SummoningCard;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class Summoning {

    private final UUID id;
    private final SummoningCard summoningCard;

    private int currentCost;
    private int currentHealth;
    private int currentStrength;

    public static Summoning fromCard(SummoningCard summoningCard, UUID id) {

        return Summoning.builder()
                .id(id)
                .summoningCard(summoningCard)
                .currentCost(summoningCard.getCostRations())
                .currentHealth(summoningCard.getHealth())
                .currentStrength(summoningCard.getStrength())
                .build();
    }

    public void setCurrentHealth(int newHealth) {

        this.currentHealth = newHealth;
    }

    public void reduceCurrentHealth(int amount) {

        this.currentHealth -= amount;
    }
}
