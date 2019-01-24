package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import java.util.*

data class DrawHandcardsEffect(private val amountOfCards: Int) {

    operator fun invoke(context: Context): Game {

        val game = context.game

        return context.targetPlayer
                ?.let { targetPlayer -> game.findPlayer(targetPlayer)
                        ?.drawCards(amountOfCards)
                        ?.let { game.updatePlayer(targetPlayer, it) }
                }
                ?: game
    }
}
