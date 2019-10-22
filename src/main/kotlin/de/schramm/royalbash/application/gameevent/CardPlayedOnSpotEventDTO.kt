package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.application.UUIDGenerator
import de.schramm.royalbash.domain.*

data class CardPlayedOnSpotEventDTO(
        val cardInstanceId: String,
        val ownerId: String,
        val targetSpotId: String) : GameEventDTO {

    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game {

        val owner = game.findPlayer(ownerId)
        val card = owner?.findHandcard(cardInstanceId)
        val targetSpot = game.findSpot(targetSpotId)

        return if (owner != null && card != null && targetSpot != null)
            game.playCard(card, owner, targetSpot)
                    .log(uuidGenerator.generateId(), "${owner.name} has played ${card.name} on a spot")
        else game
    }
}
