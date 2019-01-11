package de.schramm.royalbash.controller.service.core.effect

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game

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
