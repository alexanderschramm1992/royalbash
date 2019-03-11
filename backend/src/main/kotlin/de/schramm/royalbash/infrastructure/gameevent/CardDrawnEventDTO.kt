package de.schramm.royalbash.infrastructure.gameevent

import de.schramm.royalbash.domain.Game

data class CardDrawnEventDTO(
        var playerId: String,
        var amountOfCards: Int
                            ) : GameEventDTO {

    constructor(): this(playerId = "", amountOfCards = 0)

    override fun invoke(game: Game): Game {
        return game.findPlayer(playerId)
                ?.let { game.updatePlayer(it, it.drawCards(amountOfCards)) }
                ?: game
    }
}
