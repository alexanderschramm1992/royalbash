package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.discardCards
import de.schramm.royalbash.domain.findPlayer

data class DiscardHandcardsEffect(val amountOfCards: Int) {

    operator fun invoke(context: Context): Game {

        val game = context.game

        return context.targetPlayer
                ?.let { game.findPlayer(it)
                            ?.discardCards(amountOfCards)
                            ?.updateInGame(game, it)
                }
                ?: game
    }
}
