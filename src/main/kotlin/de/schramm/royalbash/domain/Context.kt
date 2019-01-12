package de.schramm.royalbash.domain

import java.util.*

data class Context(
        val game: Game,
        val owner: Player,
        private val targetPlayer: Player? = null,
        private val targetSpot: Spot? = null,
        private val targetCreature: Creature? = null
) {

    fun getTargetPlayer(): Optional<Player> {
        return Optional.ofNullable(targetPlayer)
    }

    fun getTargetSpot(): Optional<Spot> {
        return Optional.ofNullable(targetSpot)
    }

    fun getTargetCreature(): Optional<Creature> {
        return Optional.ofNullable(targetCreature)
    }
}
