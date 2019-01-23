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
                .map { owner -> Optional.ofNullable(owner.findHandcard(cardId))
                        .map { card -> Optional.ofNullable(game.findSpot(targetSpotId))
                                .map { targetSpot -> game.playCard(card, owner, targetSpot) }
                                .orElse(game)
                        }
                        .orElse(game) }
                .orElse(game)
    }
}
