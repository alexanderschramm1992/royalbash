package de.schramm.royalbash.controller.service.gameevent

import de.schramm.royalbash.controller.service.core.Game

data class CardPlayedOnSpotEvent(
        val cardId: String,
        val ownerId: String,
        val targetSpotId: String) : GameEvent {

    constructor(): this(cardId = "", ownerId = "", targetSpotId = "")

    override fun invoke(game: Game): Game {
        return game.findPlayer(ownerId)
                .map { owner -> owner.findHandcard(cardId)
                        .map { card -> game.findSpot(targetSpotId)
                                .map { targetSpot -> game.playCard(card, owner, targetSpot) }
                                .orElse(game) }
                        .orElse(game) }
                .orElse(game)
    }
}