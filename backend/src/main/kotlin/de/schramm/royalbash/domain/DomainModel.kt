package de.schramm.royalbash.domain

data class Game(val id: String,
                val state: State = State.OPEN,
                val player1: Player,
                val player2: Player,
                val playerOnTurn: Player = player1,
                val log: Log = Log())

data class Player(
        val id: String,
        val uuidGenerator: UUIDGenerator = UUIDGenerator(),
        val name: String = "Player",
        val hitpoints: Int = 0,
        val resources: Int = 0,
        val handcards: List<Card> = emptyList(),
        val deckcards: List<Card> = emptyList(),
        val depositcards: List<Card> = emptyList(),
        val spots: List<Spot> = listOf(
                Spot(uuidGenerator.generateId()),
                Spot(uuidGenerator.generateId()),
                Spot(uuidGenerator.generateId()),
                Spot(uuidGenerator.generateId()),
                Spot(uuidGenerator.generateId())))

data class Spot(val id: String,
                val creature: Creature? = null)

enum class State {

    OPEN, PLAYER_1_WON, PLAYER_2_WON
}

data class Log(val entries: List<Entry> = emptyList())
data class Entry(val message: String)
