package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.findCreature
import de.schramm.royalbash.domain.updateCreature

data class DealDamageToCreatureEffect(private val amountOfDamage: Int) {

    operator fun invoke(context: Context): Game {

        val game = context.game
        val actualCreature = context.targetCreature?.let(game::findCreature)

        return if (actualCreature != null) {
            val updatedCreature = actualCreature.damage(amountOfDamage)
            game.updateCreature(actualCreature to updatedCreature)
        } else game
    }
}
