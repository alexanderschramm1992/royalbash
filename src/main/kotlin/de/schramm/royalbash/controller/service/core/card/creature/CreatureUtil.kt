package de.schramm.royalbash.controller.service.core.card.creature

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Creature
import de.schramm.royalbash.controller.service.core.Game

internal object CreatureUtil {

    fun spawnCreature(creature: Creature, context: Context): Game {

        val game = context.game
        val owner = context.owner

        return context.getTargetSpot()
                .map { targetSpot -> game.findPlayer(owner)
                        .map { player -> player.updateSpot(targetSpot, targetSpot.place(creature)) }
                        .map { player -> game.updatePlayer(owner, player) }
                        .orElse(game) }
                .orElse(game)
    }
}
