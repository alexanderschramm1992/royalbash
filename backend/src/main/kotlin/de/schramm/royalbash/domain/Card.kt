package de.schramm.royalbash.domain

interface Card {
    val id: String
    val name: String
    val text: String
    val cost: Int
    val image: String

    val placeableOnSpot: Boolean
        get() = false

    operator fun invoke(context: Context): Game
}
