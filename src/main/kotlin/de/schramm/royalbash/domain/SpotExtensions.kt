package de.schramm.royalbash.domain

fun Spot.place(creature: Creature): Spot {
    return if (!creature.placeableOnSpot) {
        this
    } else copy(creature = creature)
}

fun Spot.updateCreature(oldToNew: Pair<Creature, Creature>): Spot {

    val updatedCreature = creature?.let { if (it == oldToNew.old) oldToNew.new else it }

    return copy(creature = updatedCreature)
}

fun Spot.removeCreature(creature: Creature): Spot {
    return copy(creature = if (creature == this.creature) null else this.creature)
}
