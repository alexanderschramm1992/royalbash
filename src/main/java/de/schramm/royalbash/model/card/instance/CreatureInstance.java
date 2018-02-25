package de.schramm.royalbash.model.card.instance;

import de.schramm.royalbash.model.InstanceType;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Set;
import java.util.UUID;

@Builder
@Getter
public class CreatureInstance implements AttackableCanAttack, CanEquip, CardInstance {

    private static final InstanceType INSTANCE_TYPE = InstanceType.Creature;

    private final UUID id;
    private final String name;

    private final int cost;
    private final String text;
    private final String lore;
    private final int strength;
    private final int health;

    private int currentCost;
    private int currentHealth;
    private int currentStrength;

    @Singular("equipped")
    private Set<Equippable> equippedSet;

    @Override
    public InstanceType getInstanceType() {

        return INSTANCE_TYPE;
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
