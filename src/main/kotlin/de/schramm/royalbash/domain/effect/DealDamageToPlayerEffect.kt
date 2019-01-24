package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player

data class DealDamageToPlayerEffect (private val amountOfDamage: Int) {

    operator fun invoke(context: Context): Game {
        return context.targetPlayer
                ?.let { targetPlayer ->
                    context.game
                            .findPlayer(targetPlayer)
                            ?.let { it.setHitpoints(it.hitpoints - amountOfDamage) }
                            ?.let { updateTargetPlayer(it, context) }
                            ?: context.game
                }
                ?: context.game
    }

    private fun updateTargetPlayer(updatedPlayer: Player, context: Context): Game {

        val game = context.game

        return context.targetPlayer
                ?.let { game.updatePlayer(it, updatedPlayer) }
                ?: game
    }
}
