package de.schramm.royalbash.infrastructure.controller.gameevent

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.findPlayer
import de.schramm.royalbash.domain.findSpot
import de.schramm.royalbash.domain.playCard

data class CardPlayedOnSpotEventDTO(
        val cardId: String,
        val ownerId: String,
        val targetSpotId: String) : GameEventDTO {

    override fun invoke(game: Game): Game {

        val owner = game.findPlayer(ownerId)
        val card = owner?.findHandcard(cardId)
        val targetSpot = game.findSpot(targetSpotId)

        return if (owner != null && card != null && targetSpot != null)
            game.playCard(card, owner, targetSpot)
        else game
    }
}
