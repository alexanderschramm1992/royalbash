package de.schramm.royalbash.domain

import de.schramm.royalbash.domain.card.creature.CreatureType

interface Creature : Card {

    val hitpoints: Int
    val attack: Int
    val type: CreatureType

    fun attack(context: AttackPlayerContext): Game

    fun attack(context: AttackCreatureContext): Game

    fun attack(context: AttackSpotContext): Game

    fun isDead(): Boolean {
        return hitpoints <= 0
    }

    fun reduceHitpointsBy(amountOfDamage: Int): Creature

    fun increaseAttackBy(amountOfAttack: Int): Creature
}
