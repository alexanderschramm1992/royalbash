package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.updateCreature

data class DealDamageToCreatureEffect(private val amountOfDamage: Int) {

    operator fun invoke(context: Context): Game {

        val game = context.game

        return context.targetCreature
                ?.let { game.updateCreature(it to it.damage(amountOfDamage)) }
                ?: game
    }
}
