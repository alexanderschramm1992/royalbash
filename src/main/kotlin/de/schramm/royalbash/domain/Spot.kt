package de.schramm.royalbash.domain

import java.util.*

data class Spot(
        val id: String,
        private val creature: Creature? = null) {

    fun getCreature(): Optional<Creature> {
        return Optional.ofNullable(creature)
    }

    fun place(creature: Creature): Spot {
        return if (!creature.placeableOnSpot) {
            this
        } else copy(creature = creature)
    }

    internal fun updateCreature(oldCreature: Creature, newCreature: Creature): Spot {

        val updatedCreature = getCreature()
                .map { creature -> if (creature == oldCreature) newCreature else creature }
                .orElse(null)

        return copy(creature = updatedCreature)
    }

    internal fun removeCreature(creature: Creature): Spot {
        return copy(creature = if (creature == this.creature) null else this.creature)
    }
}
