package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.*

data class TurnEndedEventDTO(val playerId: String): GameEventDTO {

    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game = game.run {

        val player = findPlayer(playerId)

        when {
            player == null         -> logPlayerMissing(uuidGenerator)
            player != playerOnTurn -> logNotTurnOfPlayer(uuidGenerator, player, playerOnTurn)
            else                   -> switchPlayerOnTurn()
                    .logTurnEnded(uuidGenerator, playerOnTurn, opponentOf(playerOnTurn))
        }
    }
}
