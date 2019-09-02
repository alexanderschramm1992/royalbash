package de.schramm.royalbash.infrastructure.gameevent

import de.schramm.royalbash.domain.Game

data class CardDrawnEventDTO(var playerId: String,
                             var amountOfCards: Int): GameEventDTO {

    override fun invoke(game: Game) = game.findPlayer(playerId)
                                              ?.let { game.updatePlayer(it, it.drawCards(amountOfCards)) }
                                      ?: game
}
