package de.schramm.royalbash.tdd.core.creature;

import de.schramm.royalbash.tdd.core.Context;
import de.schramm.royalbash.tdd.core.Creature;
import de.schramm.royalbash.tdd.core.Game;
import lombok.val;

class CreatureUtil {

    static Game spawnCreature(Creature creature, Context context) {

        val game = context.getGame();
        val owner = context.getOwner();
        val targetSpot = context.getTargetSpot();

        val updatedSpot = targetSpot.place(creature);

        return game.getPlayer(owner)
                .map(player -> player.updateSpot(targetSpot, updatedSpot))
                .map(player -> game.updatePlayer(owner, player))
                .orElse(game);
    }
}
