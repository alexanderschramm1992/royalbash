package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game

data class DealDamageToPlayerEffect (private val amountOfDamage: Int) {

    operator fun invoke(context: Context): Game {

        return context.targetPlayer
                ?.let { context.game
                            .findPlayer(it)
                            ?.setHitpoints(it.hitpoints - amountOfDamage)
                            ?.updateInGame(context.game, context.targetPlayer) }
                ?: context.game
    }
}
