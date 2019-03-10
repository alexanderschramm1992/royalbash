package de.schramm.royalbash.domain

data class Spot(
        val id: String,
        val creature: Creature? = null) {

    fun place(creature: Creature): Spot {
        return if (!creature.placeableOnSpot) {
            this
        } else copy(creature = creature)
    }

    internal fun updateCreature(oldCreature: Creature, newCreature: Creature): Spot {

        val updatedCreature = creature?.let { if (it == oldCreature) newCreature else it }

        return copy(creature = updatedCreature)
    }

    internal fun removeCreature(creature: Creature): Spot {
        return copy(creature = if (creature == this.creature) null else this.creature)
    }
}
