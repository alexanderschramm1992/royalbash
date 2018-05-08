package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class Summoning implements AttackableCanAttack {

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

    @Override
    public boolean isDead() {

        return currentHealth <= 0;
    }

    @Override
    public void receiveDamage(AttackableCanAttack attacker) {

        currentHealth -= attacker.getCurrentStrength();
    }
}
