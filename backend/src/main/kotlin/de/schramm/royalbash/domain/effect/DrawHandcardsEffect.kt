package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.drawCards
import de.schramm.royalbash.domain.findPlayer

data class DrawHandcardsEffect(private val amountOfCards: Int) {

    operator fun invoke(context: Context): Game {

        val game = context.game
        val actualPlayer = context.targetPlayer?.let(game::findPlayer)

        return if (actualPlayer != null) {
            val updatedPlayer = actualPlayer.drawCards(amountOfCards)
            game.updatePlayer(actualPlayer to updatedPlayer)
        } else game
    }
}
