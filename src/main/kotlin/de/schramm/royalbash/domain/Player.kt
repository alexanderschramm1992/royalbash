package de.schramm.royalbash.domain

import java.util.Collections.emptyList
import java.util.stream.Collectors

data class Player (
        val id: String,
        val uuidGenerator: UUIDGenerator = UUIDGenerator(),
        val name: String = "Player",
        val hitpoints: Int = 0,
        val resources: Int = 0,
        val handcards: List<Card> = emptyList(),
        val deckcards: List<Card> = emptyList(),
        val depositcards: List<Card> = emptyList(),
        val spots: List<Spot> = listOf(
                Spot(id = uuidGenerator.generateId()),
                Spot(id = uuidGenerator.generateId()),
                Spot(id = uuidGenerator.generateId()),
                Spot(id = uuidGenerator.generateId()),
                Spot(id = uuidGenerator.generateId()))
) {

    fun setHitpoints(hitpoints: Int) = copy(hitpoints = hitpoints)

    fun updateSpot(oldSpot: Spot, newSpot: Spot): Player {

        val spots = this.spots.stream()
                .map { spot -> if (spot == oldSpot) newSpot else spot }
                .collect(Collectors.toList())

        return copy(spots = spots)
    }

    fun discardCards(amountOfCards: Int): Player {

        var player = this
        for (iterator in 0 until amountOfCards) { player = player.discardCard() }
        return player
    }

    fun drawCards(amountOfCards: Int): Player {

        var player = this
        for (iterator in 0 until amountOfCards) { player = player.drawCard() }
        return player
    }

    fun findHandcard(cardId: String) = handcards.firstOrNull { cardId == it.id }

    internal fun removeHandcard(handcard: Card): Player {

        val handcards = handcards.filter { it != handcard }

        return findHandcard(handcard)
                ?.let { copy(handcards = handcards, depositcards = depositcards.plus(it)) }
                ?: this
    }

    internal fun removeCreature(creature: Creature): Player {

        val spots = spots.map { it.removeCreature(creature) }

        return findCreature(creature)
            ?.let { copy(depositcards = depositcards.plus(it), spots = spots) }
            ?: this
    }

    internal fun updateCreature(oldCreature: Creature, newCreature: Creature): Player {

        val spots = spots.map { it.updateCreature(oldCreature, newCreature) }

        return copy(spots = spots)
    }

    internal fun hasCard(card: Card) = handcards.any { card == it }

    private fun drawCard(): Player {
        return if (deckcards.isEmpty()) {
            this
        } else copy(
                deckcards = deckcards.subList(1, deckcards.size),
                handcards = handcards.plus(deckcards[0]))

    }

    private fun discardCard(): Player {
        return if (handcards.isEmpty()) {
            this
        } else copy(handcards = handcards.subList(1, handcards.size))
    }

    private fun findHandcard(card: Card) = handcards.find { card == it }

    fun findCreature(creature: Creature) = spots
        .mapNotNull { it.creature }
        .firstOrNull { creature == it }
}
