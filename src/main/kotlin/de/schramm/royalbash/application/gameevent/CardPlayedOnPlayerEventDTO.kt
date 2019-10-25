package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.*
import de.schramm.royalbash.domain.card.logCardInstanceMissing
import de.schramm.royalbash.domain.card.logCardNotInOwnerHandcards
import de.schramm.royalbash.domain.card.logOwnerOfCardMissing
import de.schramm.royalbash.domain.card.logTargetPlayerMissing

data class CardPlayedOnPlayerEventDTO(val cardInstanceId: String,
                                      val ownerId: String,
                                      val targetPlayerId: String): GameEventDTO {

    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game = game.run {

        val owner = findPlayer(ownerId)
        val card = game.findHandcard(cardInstanceId)
        val player = findPlayer(targetPlayerId)

        return when {
            card == null             -> game.logCardInstanceMissing(uuidGenerator)
            owner == null            -> game.logOwnerOfCardMissing(uuidGenerator, card)
            player == null           -> game.logTargetPlayerMissing(uuidGenerator, card)
            card !in owner.handcards -> game.logCardNotInOwnerHandcards(uuidGenerator, card, owner)
            else                     -> card(InvokationOnPlayerContext(uuidGenerator, game, owner, player))
        }
    }
}
