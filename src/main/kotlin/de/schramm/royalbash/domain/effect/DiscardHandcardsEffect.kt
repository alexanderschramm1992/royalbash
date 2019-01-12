package de.schramm.royalbash.domain.effect

import de.schramm.royalbash.domain.Context
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player

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
