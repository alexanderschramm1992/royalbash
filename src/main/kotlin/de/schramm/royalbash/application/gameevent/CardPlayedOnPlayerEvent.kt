package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.Card
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player

data class CardPlayedOnPlayerEvent(val cardId: String, val ownerId: String, val targetPlayerId: String): GameEvent {

    constructor(): this(cardId = "", ownerId = "", targetPlayerId = "")

    override fun invoke(game: Game): Game {
        return game.findPlayer(ownerId)
                ?.let { invokeWith(it, game) }
                ?: game
    }

    private fun invokeWith(owner: Player, game: Game): Game {
        return owner.findHandcard(cardId)
                ?.let { invokeWith(it, owner, game) }
                ?: game
    }

    private fun invokeWith(card: Card, owner: Player, game: Game): Game {
        return game.findPlayer(targetPlayerId)
                ?.let { game.playCard(card, owner, it) }
                ?: game
    }
}
