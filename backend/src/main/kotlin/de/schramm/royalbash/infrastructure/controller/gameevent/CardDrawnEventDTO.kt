package de.schramm.royalbash.infrastructure.controller.gameevent

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.drawCards
import de.schramm.royalbash.domain.findPlayer
import de.schramm.royalbash.domain.updatePlayer

data class CardDrawnEventDTO(val playerId: String,
                             val amountOfCards: Int):
        GameEventDTO {

    override fun invoke(game: Game) = game.findPlayer(playerId)
                                              ?.let { game.updatePlayer(it to it.drawCards(amountOfCards)) }
                                      ?: game
}
