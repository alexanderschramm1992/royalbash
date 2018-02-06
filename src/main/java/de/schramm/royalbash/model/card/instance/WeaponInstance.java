package de.schramm.royalbash.model.card.instance;

import de.schramm.royalbash.model.InstanceType;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class WeaponInstance implements Equippable, CardInstance {

    private static final InstanceType INSTANCE_TYPE = InstanceType.Weapon;

    private final UUID id;
    private final String name;

    private final int cost;
    private final int strength;
    private final int health;

    private int currentCost;
    private int currentHealth;
    private int currentStrength;

    @Override
    public InstanceType getInstanceType() {

        return INSTANCE_TYPE;
    }
}
