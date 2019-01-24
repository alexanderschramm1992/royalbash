package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.Game

data class TurnEndedEvent(val playerId: String) : GameEvent {

    constructor(): this(playerId = "")

    override fun invoke(game: Game): Game {
        return game.findPlayer(playerId)
                ?.takeIf { game.playerOnTurn == it }
                ?.let { game.copy(playerOnTurn = if (it == game.player1) game.player2 else game.player1) }
                ?: game
    }
}
