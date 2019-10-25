package de.schramm.royalbash.domain

fun Spot.place(creature: Creature): Spot = copy(creature = creature)

fun Spot.updateCreature(oldToNew: Pair<Creature, Creature>) = copy(creature = creature?.let {
    if (it.instanceId == oldToNew.old?.instanceId) oldToNew.new else it
})

fun Spot.buryCreature(creature: Creature) = copy(creature = if (creature == this.creature) null else this.creature)
