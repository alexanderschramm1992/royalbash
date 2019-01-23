package de.schramm.royalbash.domain

import java.util.*
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

    fun setHitpoints(hitpoints: Int): Player {
        return copy(hitpoints = hitpoints)
    }

    fun updateSpot(oldSpot: Spot, newSpot: Spot): Player {

        val spots = this.spots.stream()
                .map { spot -> if (spot == oldSpot) newSpot else spot }
                .collect(Collectors.toList())

        return copy(spots = spots)
    }

    fun discardCards(amountOfCards: Int): Player {

        var player = this
        for (iterator in 0 until amountOfCards) {
            player = player.discardCard()
        }

        return player
    }

    fun drawCards(amountOfCards: Int): Player {

        var player = this
        for (iterator in 0 until amountOfCards) {
            player = player.drawCard()
        }

        return player
    }

    fun findHandcard(cardId: String) = handcards.firstOrNull { cardId == it.id }

    internal fun removeHandcard(handcard: Card): Player {

        val handcards = handcards.filter { it != handcard }

        return findHandcard(handcard)
                .map { card -> copy(
                        handcards = handcards,
                        depositcards = depositcards.plus(card)
                ) }
                .orElse(this)
    }

    internal fun removeCreature(creature: Creature): Player {

        val spots = spots.map { it.removeCreature(creature) }

        return findCreature(creature)
                .map { ownedCreature -> copy(
                        depositcards = depositcards.plus(ownedCreature),
                        spots = spots
                ) }
                .orElse(this)
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

    private fun findHandcard(card: Card) = Optional.ofNullable(handcards.find { card == it })

    fun findCreature(creature: Creature): Optional<Creature> {
        return Optional.ofNullable(spots
                .map { it.getCreature() }
                .filter { it.isPresent }
                .map { it.get() }
                .firstOrNull { creature == it })
    }
}
