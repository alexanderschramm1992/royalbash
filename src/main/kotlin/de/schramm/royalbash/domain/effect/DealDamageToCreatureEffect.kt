package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game

data class DealDamageToCreatureEffect(private val amountOfDamage: Int) {

    operator fun invoke(context: Context): Game {

        val game = context.game

        return context.getTargetCreature()
                .map { creature -> game.updateCreature(creature, creature.damage(amountOfDamage)) }
                .orElse(game)
    }
}
