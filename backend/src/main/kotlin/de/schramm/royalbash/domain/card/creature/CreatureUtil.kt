package de.schramm.royalbash.domain.card.creature

import de.schramm.royalbash.domain.*

internal object CreatureUtil {

    fun spawnCreature(creature: Creature, context: Context): Game {

        val game = context.game
        val owner = context.owner

        return context.targetSpot
                ?.let { targetSpot -> game.findPlayer(owner)
                        ?.let { it.updateSpot(targetSpot to targetSpot.place(creature)) }
                        ?.let { game.updatePlayer(owner to it) }
                        ?: game }
                ?: game
    }
}
