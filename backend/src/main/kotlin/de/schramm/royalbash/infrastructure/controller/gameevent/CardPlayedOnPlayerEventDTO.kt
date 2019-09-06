package de.schramm.royalbash.infrastructure.controller.gameevent

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.findHandcard
import de.schramm.royalbash.domain.findPlayer
import de.schramm.royalbash.domain.playCard
import de.schramm.royalbash.infrastructure.controller.gameevent.GameEventDTO

data class CardPlayedOnPlayerEventDTO(val cardId: String,
                                      val ownerId: String,
                                      val targetPlayerId: String):
        GameEventDTO {

    override fun invoke(game: Game): Game {

        val owner = game.findPlayer(ownerId)
        val card = owner?.findHandcard(cardId)
        val targetPlayer = game.findPlayer(targetPlayerId)

        return if (owner != null && card != null && targetPlayer != null)
            game.playCard(card, owner, targetPlayer)
        else game
    }
}
