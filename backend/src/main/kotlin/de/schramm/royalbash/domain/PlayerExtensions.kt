package de.schramm.royalbash.domain

internal fun Player.withHitpoints(hitpoints: Int) = copy(hitpoints = hitpoints)

internal fun Player.updateSpot(oldToNew: Pair<Spot, Spot>): Player =
        copy(spots = this.spots.map { if (it == oldToNew.old) oldToNew.new else it })

internal fun Player.discardCards(amountOfCards: Int): Player {

    var player = this
    for (iterator in 0 until amountOfCards) {
        player = player.discardCard()
    }
    return player
}

internal fun Player.drawCards(amountOfCards: Int): Player {

    var player = this
    for (iterator in 0 until amountOfCards) {
        player = player.drawCard()
    }
    return player
}

internal fun Player.findHandcard(cardId: String) = handcards.firstOrNull { cardId == it.id }

internal fun Player.removeHandcard(handcard: Card): Player {

    val handcards = handcards.filter { it != handcard }

    return findHandcard(handcard)
                   ?.let { copy(handcards = handcards, depositcards = depositcards.plus(it)) }
           ?: this
}

internal fun Player.removeCreature(creature: Creature): Player {

    val spots = spots.map { it.removeCreature(creature) }

    return findCreature(creature)
                   ?.let { copy(depositcards = depositcards.plus(it), spots = spots) }
           ?: this
}

internal fun Player.updateCreature(oldToNew: Pair<Creature, Creature>): Player =
        copy(spots = this.spots.map { it.updateCreature(oldToNew) })

internal fun Player.hasCard(card: Card) = handcards.any { card == it }

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

internal fun Player.findCreature(creature: Creature) = spots
        .mapNotNull { it.creature }
        .firstOrNull { creature == it }

internal fun Player.updateInGame(game: Game, oldPlayer: Player): Game = game.updatePlayer(oldPlayer to this)
