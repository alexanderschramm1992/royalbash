package de.schramm.royalbash.domain

interface Creature : Card {

    val hitpoints: Int
    val attack: Int

    fun attack(context: AttackPlayerContext): Game

    fun attack(context: AttackCreatureContext): Game

    fun attack(context: AttackSpotContext): Game

    fun isDead(): Boolean {
        return hitpoints <= 0
    }

    fun reduceHitpointsBy(amountOfDamage: Int): Creature
}
