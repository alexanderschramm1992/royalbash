package de.schramm.royalbash.controller.service.core

interface Card {
    val id: String
    val name: String
    val cost: Int

    val placeableOnSpot: Boolean
        get() = false

    operator fun invoke(context: Context): Game
}
