package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game

class RemoveCreatureEffect {

    operator fun invoke(context: Context): Game {

        val game = context.game

        return context.targetCreature
                ?.let { game.findCreature(it) }
                ?.let { game.removeCreature(it) }
                ?: game
    }
}
