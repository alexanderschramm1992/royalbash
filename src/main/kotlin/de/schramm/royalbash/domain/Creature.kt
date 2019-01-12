package de.schramm.royalbash.domain

interface Creature : Card {

    val hitpoints: Int
    val attack: Int

    override val placeableOnSpot: Boolean
        get() = true

    fun isDead(): Boolean {
        return hitpoints <= 0
    }

    fun damage(amountOfDamage: Int): Creature
}
