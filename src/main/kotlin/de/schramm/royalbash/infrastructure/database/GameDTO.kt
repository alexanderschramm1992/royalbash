package de.schramm.royalbash.infrastructure.database

import de.schramm.royalbash.domain.*

data class GameDTO(val id: String,
                   val state: State,
                   val player1: Player,
                   val player2: Player,
                   val playerOnTurn: String,
                   val turns: List<TurnDTO>,
                   val logs: List<Log>) {

    fun toGame() = Game(id,
                        state,
                        player1,
                        player2,
                        turns.toTurns(player1, player2),
                        logs)

    private fun List<TurnDTO>.toTurns(player1: Player, player2: Player) =
            map { Turn(if (player1.id == it.playerOnTurn) player1 else player2, it.cardDrawn) }
}

data class TurnDTO(val playerOnTurn: String, val cardDrawn: Boolean)

fun Game.toDTO() = GameDTO(id,
                           state,
                           player1,
                           player2,
                           playerOnTurn.id,
                           turns.toDTO(),
                           logs)

private fun List<Turn>.toDTO() = map { TurnDTO(it.playerOnTurn.id, it.cardDrawn) }
