package de.schramm.royalbash.infrastructure.database

import de.schramm.royalbash.domain.*

data class GameDTO(
        val id: String,
        val state: State,
        val player1: Player,
        val player2: Player,
        val playerOnTurn: String,
        val logs: List<Log>) {

    fun toGame() = Game(id,
                        state,
                        player1,
                        player2,
                        if (player1.id == playerOnTurn) player1 else player2,
                        logs)
}

fun Game.toDTO() = GameDTO(id,
                           state,
                           player1,
                           player2,
                           playerOnTurn.id,
                           logs)
