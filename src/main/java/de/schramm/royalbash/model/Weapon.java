package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Weapon {

    private UUID id;
    private String name;
    private int cost;
    private int strength;
    private int health;

    public Equipment toInstance(UUID id) {

        return Equipment.builder()
                .id(id)
                .currentCost(cost)
                .currentStrength(strength)
                .currentHealth(health)
                .build();
    }
}
