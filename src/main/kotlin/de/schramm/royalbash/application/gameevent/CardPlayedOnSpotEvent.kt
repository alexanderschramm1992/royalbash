package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.Game
import java.util.*

data class CardPlayedOnSpotEvent(
        val cardId: String,
        val ownerId: String,
        val targetSpotId: String) : GameEvent {

    constructor(): this(cardId = "", ownerId = "", targetSpotId = "")

    override fun invoke(game: Game): Game {
        return game.findPlayer(ownerId)
                ?.let { Optional.ofNullable(it.findHandcard(cardId))
                            .map { card -> Optional.ofNullable(game.findSpot(targetSpotId))
                                    .map { targetSpot -> game.playCard(card, it, targetSpot) }
                                    .orElse(game)
                            }
                            .orElse(game) }
                ?: game
    }
}
