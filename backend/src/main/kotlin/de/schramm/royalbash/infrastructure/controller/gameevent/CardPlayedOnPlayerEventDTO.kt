package de.schramm.royalbash.infrastructure.controller.gameevent

import de.schramm.royalbash.application.UUIDGenerator
import de.schramm.royalbash.domain.*

data class CardPlayedOnPlayerEventDTO(val cardId: String,
                                      val ownerId: String,
                                      val targetPlayerId: String) :
        GameEventDTO {

    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game {

        val owner = game.findPlayer(ownerId)
        val card = owner?.findHandcard(cardId)
        val targetPlayer = game.findPlayer(targetPlayerId)

        return if (owner != null && card != null && targetPlayer != null)
            game.playCard(card, owner, targetPlayer)
                    .log(uuidGenerator.generateId(), "${owner.name} has played ${card.name} on ${targetPlayer.name}")
        else game
    }
}
