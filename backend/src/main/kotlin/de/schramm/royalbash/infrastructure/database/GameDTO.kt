package de.schramm.royalbash.infrastructure.database

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.State

data class GameDTO (
        val id: String,
        val state: State,
        val player1: Player,
        val player2: Player,
        val playerOnTurn: String) {

    fun toGame() = Game(
                this.id,
                this.state,
                this.player1,
                this.player2,
                if(this.player1.id == this.playerOnTurn) this.player1 else this.player2)
}

fun Game.toDTO() = GameDTO(
    this.id,
    this.state,
    this.player1,
    this.player2,
    this.playerOnTurn.id)
