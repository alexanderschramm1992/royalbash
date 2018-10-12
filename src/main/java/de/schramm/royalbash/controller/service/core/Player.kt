package de.schramm.royalbash.controller.service.core

import java.util.*
import java.util.Collections.emptyList
import java.util.stream.Collectors
import java.util.stream.Stream

data class Player (
        val id: String,
        val name: String = "Player",
        val hitpoints: Int = 0,
        val resources: Int = 0,
        val handcards: List<Card> = emptyList(),
        val deckcards: List<Card> = emptyList(),
        val depositcards: List<Card> = emptyList(),
        val spots: List<Spot> = emptyList()
) {

    fun setHitpoints(hitpoints: Int): Player {
        return copy(hitpoints = hitpoints)
    }

    fun getSpots(): Stream<Spot> {
        return spots.stream()
    }

    fun getHandcards(): Stream<Card> {
        return handcards.stream()
    }

    fun getDeckcards(): Stream<Card> {
        return deckcards.stream()
    }

    fun getDepositcards(): Stream<Card> {
        return depositcards.stream()
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

    fun findHandcard(cardId: String): Optional<Card> {
        return getHandcards()
                .filter { card -> cardId == card.id }
                .findFirst()
    }

    internal fun removeHandcard(handcard: Card): Player {

        val handcards = getHandcards()
                .filter { ownHandcard -> ownHandcard != handcard }
                .collect(Collectors.toList())

        return findHandcard(handcard)
                .map { card -> copy(
                        handcards = handcards,
                        depositcards = depositcards.plus(card)
                ) }
                .orElse(this)
    }

    internal fun removeCreature(creature: Creature): Player {

        val spots = getSpots()
                .map { spot -> spot.removeCreature(creature) }
                .collect(Collectors.toList())

        return findCreature(creature)
                .map { ownedCreature -> copy(
                        depositcards = depositcards.plus(ownedCreature),
                        spots = spots
                ) }
                .orElse(this)
    }

    internal fun updateCreature(oldCreature: Creature, newCreature: Creature): Player {

        val spots = getSpots()
                .map { spot -> spot.updateCreature(oldCreature, newCreature) }
                .collect(Collectors.toList())

        return copy(spots = spots)
    }

    internal fun reduceResources(cost: Int): Player {
        return copy(resources = resources - cost)
    }

    internal fun hasCard(card: Card): Boolean {
        return getHandcards()
                .anyMatch { handcard -> handcard == card }
    }

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

    private fun findHandcard(card: Card): Optional<Card> {
        return getHandcards()
                .filter { card == it }
                .findFirst()
    }

    fun findCreature(creature: Creature): Optional<Creature> {
        return getSpots()
                .map { it.getCreature() }
                .filter { it.isPresent }
                .map { it.get() }
                .filter { creature == it }
                .findFirst()
    }
}
