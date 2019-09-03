package de.schramm.royalbash.infrastructure.gameevent

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.findPlayer

data class TurnEndedEventDTO(val playerId: String) : GameEventDTO {

    override fun invoke(game: Game): Game {

        return game.findPlayer(playerId)
                ?.takeIf { game.playerOnTurn == it }
                ?.let { game.copy(playerOnTurn = if (it == game.player1) game.player2 else game.player1) }
                ?: game
    }
}
