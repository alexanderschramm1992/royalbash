package de.schramm.royalbash.controller.service.core.creature;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Creature;
import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CreatureMock implements Creature {

    private final boolean placeableOnSpot = true;

    private final String id;
    private final String name;
    private final int hitpoints;
    private final int attack;
    private final int cost;

    @Override
    public Creature damage(Creature attacker) {
        return this.toBuilder()
                .hitpoints(hitpoints - attacker.getAttack())
                .build();
    }

    @Override
    public boolean isDead() {
        return hitpoints <= 0;
    }

    @Override
    public Game invoke(Context context) {
        return null;
    }
}
