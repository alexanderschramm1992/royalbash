package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.findPlayer
import de.schramm.royalbash.domain.withHitpoints

data class DealDamageToPlayerEffect (private val amountOfDamage: Int) {

    operator fun invoke(context: Context): Game {

        val game = context.game
        val actualPlayer = context.targetPlayer?.let(game::findPlayer)

        return if (actualPlayer != null) {
            val updatedPlayer = actualPlayer.withHitpoints(actualPlayer.hitpoints - amountOfDamage)
            game.updatePlayer(actualPlayer to updatedPlayer)
        } else game
    }
}
