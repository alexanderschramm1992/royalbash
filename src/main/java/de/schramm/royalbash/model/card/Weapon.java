package de.schramm.royalbash.model.card;

import de.schramm.royalbash.model.InstanceType;
import de.schramm.royalbash.model.card.instance.CardInstance;
import de.schramm.royalbash.model.card.instance.WeaponInstance;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Weapon implements Card {

    private UUID id;
    private String name;
    private InstanceType instanceType = InstanceType.Weapon;
    private int cost;
    private int strength;
    private int health;

    @Override
    public CardInstance toInstance() {

        return WeaponInstance.builder()
                .id(id)
                .name(name)
                .cost(cost)
                .currentCost(cost)
                .strength(strength)
                .health(health)
                .currentStrength(strength)
                .currentHealth(health)
                .build();
    }
}
