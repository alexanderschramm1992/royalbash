package de.schramm.royalbash.domain

data class Context(
        val game: Game,
        val owner: Player,
        val targetPlayer: Player? = null,
        val targetSpot: Spot? = null,
        val targetCreature: Creature? = null)
