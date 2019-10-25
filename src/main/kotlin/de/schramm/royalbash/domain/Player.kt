package de.schramm.royalbash.domain

data class Player(val id: String,
                  val name: String = "Player",
                  val hitpoints: Int = 0,
                  val resources: Int = 0,
                  val handcards: List<Card> = emptyList(),
                  val deckcards: List<Card> = emptyList(),
                  val depositcards: List<Card> = emptyList(),
                  val spots: List<Spot> = emptyList())
