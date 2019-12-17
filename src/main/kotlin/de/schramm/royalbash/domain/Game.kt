package de.schramm.royalbash.domain

data class Game(val id: String,
                val state: State = State.OPEN,
                val player1: Player,
                val player2: Player,
                val turns: List<Turn> = listOf(Turn(player1)),
                val logs: List<Log> = emptyList())
