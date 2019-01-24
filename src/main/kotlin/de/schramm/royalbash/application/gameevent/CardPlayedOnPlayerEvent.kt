package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.Game
import java.util.*

data class CardPlayedOnPlayerEvent(
        val cardId: String,
        val ownerId: String,
        val targetPlayerId: String
) : GameEvent {

    constructor(): this(cardId = "", ownerId = "", targetPlayerId = "")

    override fun invoke(game: Game): Game {
        return game.findPlayer(ownerId)
                ?.let { Optional.ofNullable(it.findHandcard(cardId))
                            .map { card -> game.findPlayer(targetPlayerId)
                                    ?.let { targetPlayer -> game.playCard(card, it, targetPlayer) }
                                    ?:game }
                            .orElse(game) }
                ?: game
    }
}
