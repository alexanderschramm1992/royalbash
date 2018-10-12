package de.schramm.royalbash.controller.service.gameevent

import de.schramm.royalbash.controller.service.core.Game

data class CardPlayedOnPlayerEvent(
        val cardId: String,
        val ownerId: String,
        val targetPlayerId: String
) : GameEvent {

    constructor(): this(cardId = "", ownerId = "", targetPlayerId = "")

    override fun invoke(game: Game): Game {
        return game.findPlayer(ownerId)
                .map { owner -> owner.findHandcard(cardId)
                        .map { card -> game.findPlayer(targetPlayerId)
                                .map { targetPlayer -> game.playCard(card, owner, targetPlayer) }
                                .orElse(game) }
                        .orElse(game) }
                .orElse(game)
    }
}
