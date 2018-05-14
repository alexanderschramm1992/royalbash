package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class Summoning {

    private final UUID id;
    private final Card card;

    private int currentCost;
    private int currentHealth;
    private int currentStrength;

    public static Summoning fromCard(Card card, UUID id) {

        return Summoning.builder()
                .id(id)
                .card(card)
                .currentCost(card.getCostRations())
                .currentHealth(card.getHealth())
                .currentStrength(card.getStrength())
                .build();
    }

    public void setCurrentHealth(int newHealth) {

        this.currentHealth = newHealth;
    }

    public void reduceCurrentHealth(int amount) {

        this.currentHealth -= amount;
    }
}
