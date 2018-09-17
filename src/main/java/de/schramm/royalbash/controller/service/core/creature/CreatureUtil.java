package de.schramm.royalbash.controller.service.core.creature;

import de.schramm.royalbash.controller.service.core.CardOnPlayerContext;
import de.schramm.royalbash.controller.service.core.Creature;
import de.schramm.royalbash.controller.service.core.Game;
import lombok.val;

class CreatureUtil {

    static Game spawnCreature(Creature creature, CardOnPlayerContext cardOnPlayerContext) {

        val game = cardOnPlayerContext.getGame();
        val owner = cardOnPlayerContext.getOwner();
        val targetSpot = cardOnPlayerContext.getTargetSpot();

        val updatedSpot = targetSpot.place(creature);

        return game.findPlayer(owner)
                .map(player -> player.updateSpot(targetSpot, updatedSpot))
                .map(player -> game.updatePlayer(owner, player))
                .orElse(game);
    }

    static boolean isDead(Creature creature) {
        return creature.getHitpoints() <= 0;
    }
}
