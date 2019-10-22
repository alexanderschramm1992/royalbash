package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.findCreature
import de.schramm.royalbash.domain.buryCreature

class RemoveCreatureEffect {

    operator fun invoke(context: Context): Game {

        val game = context.game
        val actualCreature = context.targetCreature?.let(game::findCreature)

        return if (actualCreature != null) game.buryCreature(actualCreature)
        else game
    }
}
