package de.schramm.royalbash.controller.service.core

import de.schramm.royalbash.controller.service.core.card.CardMock
import de.schramm.royalbash.controller.service.core.card.creature.CreatureMock
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Index
import org.junit.Test
import java.util.stream.Collectors

class PlayerTest {

    @Test
    fun should_deliver_hitpoints() {

        // Given
        val testee = Player.builder()
                .hitpoints(30)
                .build()

        // When
        val hitpoints = testee.hitpoints

        // Then
        assertThat(hitpoints).isEqualTo(30)
    }

    @Test
    fun should_change_hitpoints() {

        // Given
        val testee = Player.builder()
                .hitpoints(30)
                .build()

        // When
        val player = testee.setHitpoints(10)

        // Then
        assertThat(player.hitpoints).isEqualTo(10)
    }

    @Test
    fun should_deliver_handcards() {

        // Given
        val handcard = CardMock("Id 1")
        val testee = Player.builder()
                .handcard(handcard)
                .build()

        // When
        val cards = testee.handcards

        // Then
        assertThat(cards)
                .hasSize(1)
                .contains(handcard)
    }

    @Test
    fun should_deliver_handcards_in_order() {

        // Given
        val handcard1 = CardMock("Id 1")
        val handcard2 = CardMock("Id 2")
        val testee = Player.builder()
                .handcard(handcard1)
                .handcard(handcard2)
                .build()

        // When
        val cards = testee.handcards.collect(Collectors.toList())

        // Then
        assertThat(cards)
                .contains(handcard1, Index.atIndex(0))
                .contains(handcard2, Index.atIndex(1))
    }

    @Test
    fun should_deliver_deposit() {

        // Given
        val card = CardMock("Id 1")
        val testee = Player.builder()
                .depositcard(card)
                .build()

        // When
        val cards = testee.depositcards

        // Then
        assertThat(cards)
                .hasSize(1)
                .contains(card)
    }

    @Test
    fun should_remove_handcard_and_add_it_ro_deposit() {

        // Given
        val card = CardMock("Id 1")
        val testee = Player.builder()
                .handcard(card)
                .build()

        // When
        val player = testee.removeHandcard(card)

        // Then
        assertThat(player.handcards).hasSize(0)
        assertThat(player.depositcards)
                .hasSize(1)
                .contains(card)
    }

    @Test
    fun should_not_remove_handcard_if_it_cannot_be_found() {

        // Given
        val handcard1 = CardMock("Id 1")
        val handcard2 = CardMock("Id 2")
        val testee = Player.builder()
                .handcard(handcard1)
                .build()

        // When
        val player = testee.removeHandcard(handcard2)

        // Then
        assertThat(player.handcards)
                .hasSize(1)
                .contains(handcard1)
    }

    @Test
    fun should_retain_order_when_removing_card() {

        // Given
        val handcard1 = CardMock("Id 1")
        val handcard2 = CardMock("Id 2")
        val handcard3 = CardMock("Id 3")
        val testee = Player.builder()
                .handcard(handcard1)
                .handcard(handcard2)
                .handcard(handcard3)
                .build()

        // When
        val player = testee.removeHandcard(handcard2)

        // Then
        assertThat(player.handcards)
                .hasSize(2)
                .contains(handcard1, Index.atIndex(0))
                .contains(handcard3, Index.atIndex(1))
    }

    @Test
    fun should_remove_creature_and_add_it_to_deposit() {

        // Given
        val creature = CreatureMock("Id 1")
        val spot = Spot.builder()
                .creature(creature)
                .build()
        val testee = Player.builder()
                .spot(spot)
                .build()

        // When
        val player = testee.removeCreature(creature)

        // Then
        val creaturesOfPlayer = player.spots
                .map { it.creature }
                .filter { it.isPresent }
                .map { it.get() }
                .collect(Collectors.toList())
        assertThat(creaturesOfPlayer).isEmpty()
        assertThat(player.depositcards)
                .hasSize(1)
                .contains(creature)
    }

    @Test
    fun should_not_remove_creature() {

        // Given
        val creature = CreatureMock("Id 1")
        val spot = Spot.builder()
                .creature(creature)
                .build()
        val testee = Player.builder()
                .spot(spot)
                .build()

        // When
        val player = testee.removeCreature(CreatureMock("Id 2"))

        // Then
        val creaturesOfPlayer = player.spots
                .map { it.creature }
                .filter { it.isPresent }
                .map { it.get() }
                .collect(Collectors.toList())
        assertThat(creaturesOfPlayer)
                .hasSize(1)
                .contains(creature)
    }

    @Test
    fun should_update_creature() {

        // Given
        val creature = CreatureMock("Id 1")
        val spot = Spot.builder()
                .creature(creature)
                .build()
        val testee = Player.builder()
                .spot(spot)
                .build()
        val updatedCreature = CreatureMock(
                "Id 1",
                hitpoints = 12
        )

        // When
        val player = testee.updateCreature(creature, updatedCreature)

        // Then
        val creaturesOfPlayer = player.spots
                .map { it.creature }
                .filter { it.isPresent }
                .map { it.get() }
                .collect(Collectors.toList())
        assertThat(creaturesOfPlayer)
                .contains(updatedCreature)
                .doesNotContain(creature)
    }

    @Test
    fun should_not_update_creature() {

        // Given
        val creature = CreatureMock("Id 1")
        val spot = Spot.builder()
                .build()
        val testee = Player.builder()
                .spot(spot)
                .build()

        // When
        val player = testee.updateCreature(creature, creature)

        // Then
        val creaturesOfPlayer = player.spots
                .map { it.creature }
                .filter { it.isPresent }
                .map { it.get() }
                .collect(Collectors.toList())
        assertThat(creaturesOfPlayer).doesNotContain(creature)
    }

    @Test
    @Throws(Exception::class)
    fun should_find_handcard() {

        // Given
        val card = CreatureMock("Id 1")
        val testee = Player.builder()
                .handcard(card)
                .build()

        // When
        val foundCard = testee.findHandcard("Id 1")
                .orElseThrow { Exception("Card not present") }

        // Then
        assertThat(foundCard).isEqualTo(card)
    }

    @Test
    fun should_not_find_handcard() {

        // Given
        val testee = Player.builder().build()

        // When
        val card = testee.findHandcard("Id 1")

        // Then
        assertThat(card.isPresent).isFalse()
    }
}
