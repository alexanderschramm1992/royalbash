package de.schramm.royalbash.controller.service.gameevent

import de.schramm.royalbash.controller.service.core.Game

data class TurnEndedEvent(val playerId: String) : GameEvent {

    constructor(): this(playerId = "")

    override fun invoke(game: Game): Game {
        return game.findPlayer(playerId)
                .filter { player -> game.playerOnTurn == player }
                .map { player -> game.copy(playerOnTurn = if (player == game.player1) game.player2 else game.player1) }
                .orElse(game)
    }
}
