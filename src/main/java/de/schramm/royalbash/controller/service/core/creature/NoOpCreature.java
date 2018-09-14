package de.schramm.royalbash.controller.service.core.creature;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Creature;
import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class NoOpCreature implements Creature {

    private final String id;
    private final int hitpoints;
    private final int attack;
    private final boolean isPlaceableOnSpot = true;

    @Override
    public Game invoke(Context context) {
        return CreatureUtil.spawnCreature(this, context);
    }

    @Override
    public Creature damage(Creature attacker) {
        return this.toBuilder()
                .hitpoints(hitpoints - attacker.getAttack())
                .build();
    }

    @Override
    public boolean isDead() {
        return CreatureUtil.isDead(this);
    }
}
