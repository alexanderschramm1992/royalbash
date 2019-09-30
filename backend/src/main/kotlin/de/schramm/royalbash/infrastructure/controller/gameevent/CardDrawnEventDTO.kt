package de.schramm.royalbash.infrastructure.controller.gameevent

import de.schramm.royalbash.application.UUIDGenerator
import de.schramm.royalbash.domain.*

data class CardDrawnEventDTO(val playerId: String,
                             val amountOfCards: Int) : GameEventDTO {

    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game {

        val player = game.findPlayer(playerId)

        return player
                ?.let { game.updatePlayer(it to it.drawCards(amountOfCards)) }
                ?.let { game.log(Log(uuidGenerator.generateId(), "${player.name} has drawn a card")) }
                ?: game
    }
}
