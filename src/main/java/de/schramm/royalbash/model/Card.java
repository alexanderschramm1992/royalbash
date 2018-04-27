package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Card {

    private UUID id;
    private String name;
    private String image;
    private String type;
    private String text;
    private int cost;
    private int strength;
    private int health;

    public Summoning toInstance(UUID id) {

        return Summoning.builder()
                .id(id)
                .card(this)
                .currentCost(cost)
                .currentStrength(strength)
                .currentHealth(health)
                .build();
    }
}
