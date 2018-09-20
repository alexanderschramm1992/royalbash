package de.schramm.royalbash.controller.service.core.card.creature;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Creature;
import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class NoOpCreature implements Creature {

    private final String id;
    private final String name;
    private final int hitpoints;
    private final int attack;
    private final int cost;
    private final boolean isPlaceableOnSpot = true;

    @Override
    public Game invoke(Context context) {
        return CreatureUtil.spawnCreature(this, context);
    }

    @Override
    public Creature damage(int amountOfDamage) {
        return this.toBuilder()
                .hitpoints(hitpoints - amountOfDamage)
                .build();
    }

    @Override
    public boolean isDead() {
        return CreatureUtil.isDead(this);
    }
}
