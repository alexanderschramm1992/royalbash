package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player

data class DealDamageToPlayerEffect (private val amountOfDamage: Int) {

    operator fun invoke(context: Context): Game {
        return context.getTargetPlayer()
                .map { targetPlayer ->
                    context.game
                            .findPlayer(targetPlayer)
                            .map { player -> player.setHitpoints(player.hitpoints - amountOfDamage) }
                            .map { player -> updateTargetPlayer(player, context) }
                            .orElse(context.game)
                }
                .orElse(context.game)
    }

    private fun updateTargetPlayer(updatedPlayer: Player, context: Context): Game {

        val game = context.game

        return context.getTargetPlayer()
                .map { targetPlayer -> game.updatePlayer(targetPlayer, updatedPlayer) }
                .orElse(game)
    }
}
