package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.Game

data class CardDrawnEvent(
        var playerId: String,
        var amountOfCards: Int
) : GameEvent {

    constructor(): this(playerId = "", amountOfCards = 0)

    override fun invoke(game: Game): Game {
        return game.findPlayer(playerId)
                .map { player -> game.updatePlayer(player, player.drawCards(amountOfCards)) }
                .orElse(game)
    }
}
