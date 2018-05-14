package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class SummoningCard {

    private UUID id;
    private String name;
    private String image;
    private String type;
    private String subType;
    private String text;
    private String lore;
    private int costRations;
    private int costMaterial;
    private int costBlessing;
    private int strength;
    private int health;

    public Summoning toInstance(UUID id) {

        return Summoning.builder()
                .id(id)
                .summoningCard(this)
                .currentCost(costRations)
                .currentStrength(strength)
                .currentHealth(health)
                .build();
    }
}
