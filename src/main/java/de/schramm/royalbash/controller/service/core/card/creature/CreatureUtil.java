package de.schramm.royalbash.controller.service.core.card.creature;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Creature;
import de.schramm.royalbash.controller.service.core.Game;
import lombok.val;

class CreatureUtil {

    static Game spawnCreature(Creature creature, Context context) {

        val game = context.getGame();
        val owner = context.getOwner();

        return context.getTargetSpot()
                .map(targetSpot -> game.findPlayer(owner)
                        .map(player -> player.updateSpot(targetSpot, targetSpot.place(creature)))
                        .map(player -> game.updatePlayer(owner, player))
                        .orElse(game))
                .orElse(game);
    }
}
