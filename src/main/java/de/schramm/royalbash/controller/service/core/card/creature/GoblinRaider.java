package de.schramm.royalbash.controller.service.core.card.creature;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Creature;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.effect.DiscardHandcardsEffect;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class GoblinRaider implements Creature {

    private final String id;
    private final int hitpoints;
    private final int attack;
    private final int cost;

    private final String name = "Goblin Raider";
    private final DiscardHandcardsEffect effect = DiscardHandcardsEffect.builder()
            .amountOfCards(1)
            .build();
    private final boolean isPlaceableOnSpot = true;

    @Override
    public Game invoke(Context context) {
        return effect.invoke(context.toBuilder()
            .game(CreatureUtil.spawnCreature(this, context))
            .build());
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
