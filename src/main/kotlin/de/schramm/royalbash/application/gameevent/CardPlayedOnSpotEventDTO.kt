package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.*

data class CardPlayedOnSpotEventDTO(
        val cardInstanceId: String,
        val ownerId: String,
        val targetSpotId: String): GameEventDTO {

    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game = game.run {

        val owner = findPlayer(ownerId)
        val card = game.findHandcard(cardInstanceId)
        val spot = findSpot(targetSpotId)

        return when {
            card == null             -> game.logCardInstanceMissing(uuidGenerator)
            owner == null            -> game.logOwnerOfCardMissing(uuidGenerator, card)
            spot == null             -> game.logTargetSpotMissing(uuidGenerator, card)
            owner != playerOnTurn    -> game.logOwnerOfCardNotOnTurn(uuidGenerator, card, owner)
            card !in owner.handcards -> game.logCardNotInOwnerHandcards(uuidGenerator, card, owner)
            else                     -> card(InvokationOnSpotContext(uuidGenerator, game, owner, spot))
        }
    }
}
