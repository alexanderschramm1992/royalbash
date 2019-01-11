package de.schramm.royalbash.controller.service.core.effect

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game

data class DealDamageToCreatureEffect(private val amountOfDamage: Int) {

    operator fun invoke(context: Context): Game {

        val game = context.game

        return context.getTargetCreature()
                .map { creature -> game.updateCreature(creature, creature.damage(amountOfDamage)) }
                .orElse(game)
    }
}
