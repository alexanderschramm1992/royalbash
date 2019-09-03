package de.schramm.royalbash.domain

import java.util.*

data class Game(val id: String,
                val state: State = State.OPEN,
                val player1: Player,
                val player2: Player,
                val playerOnTurn: Player = player1)

data class Player(
        val id: String,
        val uuidGenerator: UUIDGenerator = UUIDGenerator(),
        val name: String = "Player",
        val hitpoints: Int = 0,
        val resources: Int = 0,
        val handcards: List<Card> = Collections.emptyList(),
        val deckcards: List<Card> = Collections.emptyList(),
        val depositcards: List<Card> = Collections.emptyList(),
        val spots: List<Spot> = listOf(
                Spot(id = uuidGenerator.generateId()),
                Spot(id = uuidGenerator.generateId()),
                Spot(id = uuidGenerator.generateId()),
                Spot(id = uuidGenerator.generateId()),
                Spot(id = uuidGenerator.generateId())))

data class Spot(val id: String,
                val creature: Creature? = null)
