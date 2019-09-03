package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.findPlayer
import de.schramm.royalbash.domain.withHitpoints

data class DealDamageToPlayerEffect (private val amountOfDamage: Int) {

    operator fun invoke(context: Context): Game {

        return context.targetPlayer
                ?.let { context.game
                            .findPlayer(it)
                            ?.withHitpoints(it.hitpoints - amountOfDamage)
                            ?.updateInGame(context.game, context.targetPlayer) }
                ?: context.game
    }
}
