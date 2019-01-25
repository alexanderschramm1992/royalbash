package de.schramm.royalbash.infrastructure.gameevent

import de.schramm.royalbash.domain.Game

data class CardPlayedOnSpotEventDTO(
        val cardId: String,
        val ownerId: String,
        val targetSpotId: String) : GameEventDTO {

    constructor(): this(cardId = "", ownerId = "", targetSpotId = "")

    override fun invoke(game: Game): Game {

        val owner = game.findPlayer(ownerId)
        val card = owner?.findHandcard(cardId)
        val targetSpot = game.findSpot(targetSpotId)

        return if (owner != null && card != null && targetSpot != null) {
            game.playCard(card, owner, targetSpot)
        } else game
    }
}
