package de.schramm.royalbash.controller.service.core.creature;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Creature;
import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;
import lombok.val;

@Value
@Builder(toBuilder = true)
public class GoblinRaider implements Creature {

    private final String id;
    private final int hitpoints;
    private final int attack;
    private final boolean isPlaceableOnSpot = true;

    @Override
    public Game invoke(Context context) {

        val game = CreatureUtil.spawnCreature(this, context);

        return game.findPlayer(context.getTargetPlayer())
                .map(player -> player.discardCards(1))
                .map(player -> game.updatePlayer(context.getTargetPlayer(), player))
                .orElse(game);
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
