package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.application.UUIDGenerator
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.findHandcard
import de.schramm.royalbash.domain.findPlayer
import de.schramm.royalbash.domain.playCard

data class CardPlayedOnPlayerEventDTO(val cardInstanceId: String,
                                      val ownerId: String,
                                      val targetPlayerId: String) :
        GameEventDTO {

    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game {

        val owner = game.findPlayer(ownerId)
        val card = owner?.findHandcard(cardInstanceId)
        val targetPlayer = game.findPlayer(targetPlayerId)

        return if (owner != null && card != null && targetPlayer != null)
            game.playCard(card, owner, targetPlayer)
                    ?.log(uuidGenerator.id(), "${owner.name} has played ${card.name} on ${targetPlayer.name}")
                    ?: game
        else game
    }
}
