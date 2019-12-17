package de.schramm.royalbash.domain

import de.schramm.royalbash.domain.card.creature.CreatureType

val Player.creatures get() = spots.mapNotNull(Spot::creature)

fun Player.withHitpoints(hitpoints: Int) = copy(hitpoints = hitpoints)

fun Player.creaturesOfType(type: CreatureType) = spots.filter { it.creature?.type == type ?: false }

fun Player.findSpot(spot: Spot): Spot? = spots.find { it == spot }

fun Player.findSpot(spotId: String): Spot? = spots.find { it.id == spotId }

fun Player.updateSpot(oldSpotId: String, transition: (spot: Spot) -> Spot): Player =
        findSpot(oldSpotId)?.let { updateSpot(it, transition) } ?: this

fun Player.updateSpot(oldSpot: Spot, transition: (spot: Spot) -> Spot): Player =
        findSpot(oldSpot)?.let { updateSpot(it to transition(it)) } ?: this

fun Player.updateCreatureOnSpot(spot: Spot, updateCreature: (Creature?) -> Creature?): Player =
        updateSpot(spot to spot.copy(creature = updateCreature(spot.creature)))

fun Player.updateSpot(oldToNew: Pair<Spot, Spot>): Player =
        copy(spots = this.spots.map { if (it == oldToNew.old) oldToNew.new else it })

fun Player.increaseResourcesBy(amount: Int): Player = copy(resources = resources + amount)

fun Player.reduceResourcesBy(amount: Int): Player = copy(resources = resources - amount)

fun Player.reduceHitpointsBy(amount: Int): Player = copy(hitpoints = hitpoints - amount)

fun Player.discardCards(amountOfCards: Int): Player {

    var player = this
    for (iterator in 0 until amountOfCards) {
        player = player.discardCard()
    }
    return player
}

fun Player.drawCards(amountOfCards: Int): Player {

    var player = this
    for (iterator in 0 until amountOfCards) {
        player = player.drawCard()
    }
    return player
}

fun Player.damage(amount: Int) = copy(hitpoints = hitpoints - amount)

fun Player.findHandcard(instanceId: String) = handcards.firstOrNull { instanceId == it.instanceId }

fun Player.discardHandcard(handcard: Card): Player {

    val handcards = handcards.filter { it != handcard }

    return findHandcard(handcard)
                   ?.let { copy(handcards = handcards, depositcards = depositcards.plus(it)) }
           ?: this
}

fun Player.removeHandcard(handcard: Card): Player = copy(handcards = this.handcards.filter { it != handcard })

fun Player.buryCreature(creature: Creature): Player {

    val spots = spots.map { it.buryCreature(creature) }

    return findCreature(creature)
                   ?.let { copy(depositcards = depositcards.plus(it), spots = spots) }
           ?: this
}

fun Player.updateCreature(oldToNew: Pair<Creature, Creature>): Player =
        copy(spots = this.spots.map { it.updateCreature(oldToNew) })

fun Player.hasCard(card: Card) = handcards.any { card == it }

private fun Player.drawCard(): Player {
    return if (deckcards.isEmpty()) this
    else copy(deckcards = deckcards.subList(1, deckcards.size),
              handcards = handcards.plus(deckcards[0]))
}

private fun Player.discardCard(): Player {
    return if (handcards.isEmpty()) this
    else copy(handcards = handcards.subList(1, handcards.size))
}

private fun Player.findHandcard(card: Card) = handcards.find { card == it }

fun Player.findCreature(creature: Creature) = spots
        .mapNotNull { it.creature }
        .firstOrNull { creature == it }

fun Player.updateInGame(game: Game, oldPlayer: Player): Game = game.updatePlayer(oldPlayer to this)
