package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import java.util.*

class RemoveCreatureEffect {

    operator fun invoke(context: Context): Game {

        val game = context.game

        return context.getTargetCreature()
                .map { Optional.ofNullable(game.findCreature(it)) }
                .filter { it.isPresent }
                .map { it.get() }
                .map { game.removeCreature(it) }
                .orElse(game)
    }
}
