package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Creature
import de.schramm.royalbash.domain.Game

internal object CreatureUtil {

    fun spawnCreature(creature: Creature, context: Context): Game {

        val game = context.game
        val owner = context.owner

        return context.getTargetSpot()
                .map { targetSpot -> game.findPlayer(owner)
                        ?.let { it.updateSpot(targetSpot, targetSpot.place(creature)) }
                        ?.let { game.updatePlayer(owner, it) }
                        ?: game}
                .orElse(game)
    }
}
