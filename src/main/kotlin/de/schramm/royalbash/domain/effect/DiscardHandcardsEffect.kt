package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import java.util.*

data class DiscardHandcardsEffect(val amountOfCards: Int) {

    operator fun invoke(context: Context): Game {

        val game = context.game

        return context.getTargetPlayer()
                .flatMap { targetPlayer ->
                    Optional.ofNullable(game.findPlayer(targetPlayer)
                            ?.discardCards(amountOfCards)
                            ?.let { game.updatePlayer(targetPlayer, it) })
                }
                .orElse(game)
    }
}
