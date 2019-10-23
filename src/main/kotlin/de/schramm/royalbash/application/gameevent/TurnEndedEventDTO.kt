package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.application.UUIDGenerator
import de.schramm.royalbash.domain.*

data class TurnEndedEventDTO(val playerId: String = ""): GameEventDTO {

    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game {

        return game.findPlayer(playerId)
                ?.takeIf { game.playerOnTurn == it }
                ?.let { game.switchPlayerOnTurn() }
                       ?.log(uuidGenerator.id(),
                             "${game.opponentOf(
                                     game.playerOnTurn).name} ended the turn, it is now ${game.playerOnTurn.name}'s turn")
                ?: game
    }
}
