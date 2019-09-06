package de.schramm.royalbash.infrastructure.controller.gameevent

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.findPlayer
import de.schramm.royalbash.domain.switchPlayerOnTurn

data class TurnEndedEventDTO(val playerId: String = ""):
        GameEventDTO {

    override fun invoke(game: Game): Game {

        return game.findPlayer(playerId)
                ?.takeIf { game.playerOnTurn == it }
                ?.let { game.switchPlayerOnTurn() }
                ?: game
    }
}
