package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game

data class DrawHandcardsEffect(private val amountOfCards: Int) {

    operator fun invoke(context: Context): Game {

        val game = context.game

        return context.targetPlayer
                ?.let { game.findPlayer(it)
                            ?.drawCards(amountOfCards)
                            ?.updateInGame(game, it)
                }
                ?: game
    }
}
