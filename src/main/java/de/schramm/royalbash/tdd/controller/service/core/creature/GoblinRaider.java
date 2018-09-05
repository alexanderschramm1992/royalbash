package de.schramm.royalbash.tdd.controller.service.core.creature;

import de.schramm.royalbash.tdd.controller.service.core.Context;
import de.schramm.royalbash.tdd.controller.service.core.Creature;
import de.schramm.royalbash.tdd.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;
import lombok.val;

@Value
@Builder(toBuilder = true)
public class GoblinRaider implements Creature {

    private final int hitpoints;
    private final int attack;

    @Override
    public Game invoke(Context context) {

        val game = CreatureUtil.spawnCreature(this, context);

        return game.getPlayer(context.getTargetPlayer())
                .map(player -> player.discardCards(1))
                .map(player -> game.updatePlayer(context.getTargetPlayer(), player))
                .orElse(game);
    }

    @Override
    public boolean canBePlacedOnSpot() {
        return true;
    }

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
}
