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
                .map { owner -> Optional.ofNullable(owner.findHandcard(cardId))
                        .map { card -> game.findPlayer(targetPlayerId)
                                .map { targetPlayer -> game.playCard(card, owner, targetPlayer) }
                                .orElse(game) }
                        .orElse(game) }
                .orElse(game)
    }
}
