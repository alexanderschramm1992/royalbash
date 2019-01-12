package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game

data class DrawHandcardsEffect(private val amountOfCards: Int) {

    operator fun invoke(context: Context): Game {

        val game = context.game

        return context.getTargetPlayer()
                .flatMap { targetPlayer -> game.findPlayer(targetPlayer)
                        .map { player -> player.drawCards(amountOfCards) }
                        .map { player -> game.updatePlayer(targetPlayer, player) }}
                .orElse(game)
    }
}
