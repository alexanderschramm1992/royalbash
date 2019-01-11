package de.schramm.royalbash.controller.service.core.effect

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game

class RemoveCreatureEffect {

    operator fun invoke(context: Context): Game {

        val game = context.game

        return context.getTargetCreature()
                .map { game.findCreature(it) }
                .filter { it.isPresent }
                .map { it.get() }
                .map { game.removeCreature(it) }
                .orElse(game)
    }
}
