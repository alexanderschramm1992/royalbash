package de.schramm.royalbash.controller.service.core.effect

import de.schramm.royalbash.controller.service.core.Context
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player

data class DiscardHandcardsEffect(val amountOfCards: Int) {

    operator fun invoke(context: Context): Game {

        val game = context.game

        return context.getTargetPlayer()
                .flatMap { targetPlayer ->
                    game.findPlayer(targetPlayer)
                            .map<Player> { player -> player.discardCards(amountOfCards) }
                            .map { player -> game.updatePlayer(targetPlayer, player) }
                }
                .orElse(game)
    }
}
