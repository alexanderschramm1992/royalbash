package de.schramm.royalbash.infrastructure.gameevent

import de.schramm.royalbash.domain.Game

data class TurnEndedEventDTO(val playerId: String) : GameEventDTO {

    constructor(): this(playerId = "")

    override fun invoke(game: Game): Game {

        return game.findPlayer(playerId)
                ?.takeIf { game.playerOnTurn == it }
                ?.let { game.copy(playerOnTurn = if (it == game.player1) game.player2 else game.player1) }
                ?: game
    }
}
