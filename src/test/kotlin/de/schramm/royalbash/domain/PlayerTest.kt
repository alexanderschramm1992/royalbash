package de.schramm.royalbash.domain

import de.schramm.royalbash.domain.card.CardMock
import de.schramm.royalbash.domain.card.creature.CreatureMock
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Index.atIndex
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun should_deliver_hitpoints() {

        // Given
        val testee = Player("Id 1", hitpoints = 30)

        // When
        val hitpoints = testee.hitpoints

        // Then
        assertThat(hitpoints).isEqualTo(30)
    }

    @Test
    fun should_change_hitpoints() {

        // Given
        val testee = Player("Id 1", hitpoints = 30)

        // When
        val player = testee.withHitpoints(10)

        // Then
        assertThat(player.hitpoints).isEqualTo(10)
    }

    @Test
    fun should_deliver_handcards() {

        // Given
        val handcard = CardMock(id = "Id 1", instanceId = "InstanceId 1")
        val testee = Player("Id 1", handcards = listOf(handcard))

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
        val handcard1 = CardMock(id = "Id 1", instanceId = "InstanceId 1")
        val handcard2 = CardMock(id = "Id 2", instanceId = "InstanceId 2")
        val testee = Player("Id 3", handcards = listOf(handcard1, handcard2))

        // When
        val cards = testee.handcards

        // Then
        assertThat(cards)
                .contains(handcard1, atIndex(0))
                .contains(handcard2, atIndex(1))
    }

    @Test
    fun should_deliver_deposit() {

        // Given
        val card = CardMock(id = "Id 1", instanceId = "InstanceId 1")
        val testee = Player("Id 2", depositcards = listOf(card))

        // When
        val cards = testee.depositcards

        // Then
        assertThat(cards)
                .hasSize(1)
                .contains(card)
    }

    @Test
    fun should_discard_handcard_and_add_it_to_deposit() {

        // Given
        val card = CardMock(id = "Id 1", instanceId = "InstanceId 1")
        val testee = Player("Id 2", handcards = listOf(card))

        // When
        val player = testee.discardHandcard(card)

        // Then
        assertThat(player.handcards).hasSize(0)
        assertThat(player.depositcards)
                .hasSize(1)
                .contains(card)
    }

    @Test
    fun should_not_discard_handcard_if_it_cannot_be_found() {

        // Given
        val handcard1 = CardMock(id = "Id 1", instanceId = "InstanceId 1")
        val handcard2 = CardMock(id = "Id 2", instanceId = "InstanceId 2")
        val testee = Player("Id 3", handcards = listOf(handcard1))

        // When
        val player = testee.discardHandcard(handcard2)

        // Then
        assertThat(player.handcards)
                .hasSize(1)
                .contains(handcard1)
    }

    @Test
    fun should_retain_order_when_discarding_card() {

        // Given
        val handcard1 = CardMock(id = "Id 1", instanceId = "InstanceId 1")
        val handcard2 = CardMock(id = "Id 2", instanceId = "InstanceId 2")
        val handcard3 = CardMock(id = "Id 3", instanceId = "InstanceId 3")
        val testee = Player("Id 4", handcards = listOf(handcard1, handcard2, handcard3))

        // When
        val player = testee.discardHandcard(handcard2)

        // Then
        assertThat(player.handcards)
                .hasSize(2)
                .contains(handcard1, atIndex(0))
                .contains(handcard3, atIndex(1))
    }

    @Test
    fun should_remove_handcard() {

        // Given
        val handcard = CardMock(id = "Id 1", instanceId = "InstanceId 1")
        val testee = Player(id = "Id 2", handcards = listOf(handcard))

        // When
        val player = testee.removeHandcard(handcard)

        // Then
        assertThat(player.handcards).isEmpty()
    }

    @Test
    fun should_not_remove_handcard_if_it_cannot_be_found() {

        // Given
        val handcard1 = CardMock(id = "Id 1", instanceId = "InstanceId 1")
        val handcard2 = CardMock(id = "Id 2", instanceId = "InstanceId 2")
        val testee = Player(id = "Id 3", handcards = listOf(handcard1))

        // When
        val player = testee.removeHandcard(handcard2)

        // Then
        assertThat(player.handcards)
                .hasSize(1)
                .contains(handcard2)
    }

    @Test
    fun should_retain_order_when_removing_card() {

        // Given
        val handcard1 = CardMock(id = "Id 1", instanceId = "InstanceId 1")
        val handcard2 = CardMock(id = "Id 2", instanceId = "InstanceId 2")
        val handcard3 = CardMock(id = "Id 3", instanceId = "InstanceId 3")
        val testee = Player(id = "Id 4", handcards = listOf(handcard1, handcard2, handcard3))

        // When
        val player = testee.removeHandcard(handcard2)

        // Then
        assertThat(player.handcards)
                .hasSize(2)
                .contains(handcard1, atIndex(0))
                .contains(handcard3, atIndex(2))
    }

    @Test
    fun should_bury_creature_and_add_it_to_deposit() {

        // Given
        val creature = CreatureMock(id = "Id 1", instanceId = "InstanceId 1")
        val spot = Spot(id = "spot", creature = creature)
        val testee = Player("Id 2", spots = listOf(spot))

        // When
        val player = testee.buryCreature(creature)

        // Then
        val creaturesOfPlayer = player.spots.mapNotNull { it.creature }
        assertThat(creaturesOfPlayer).isEmpty()
        assertThat(player.depositcards)
                .hasSize(1)
                .contains(creature)
    }

    @Test
    fun should_not_bury_creature() {

        // Given
        val creature = CreatureMock(id = "Id 1", instanceId = "InstanceId 1")
        val spot = Spot(id = "spot", creature = creature)
        val testee = Player("Id 2", spots = listOf(spot))

        // When
        val player = testee.buryCreature(CreatureMock(id = "Id 2", instanceId = "InstanceId 2"))

        // Then
        val creaturesOfPlayer = player.spots.mapNotNull { it.creature }
        assertThat(creaturesOfPlayer)
                .hasSize(1)
                .contains(creature)
    }

    @Test
    fun should_update_creature() {

        // Given
        val creature = CreatureMock(id = "Id 1", instanceId = "InstanceId 1")
        val spot = Spot(id = "spot", creature = creature)
        val testee = Player("Id 2", spots = listOf(spot))
        val updatedCreature = CreatureMock(id = "Id 1",
                                           instanceId = "InstanceId 1",
                                           hitpoints = 12)

        // When
        val player = testee.updateCreature(creature to updatedCreature)

        // Then
        val creaturesOfPlayer = player.spots.mapNotNull { it.creature }
        assertThat(creaturesOfPlayer)
                .contains(updatedCreature)
                .doesNotContain(creature)
    }

    @Test
    fun should_not_update_creature() {

        // Given
        val creature = CreatureMock(id = "Id 1", instanceId = "InstanceId 1")
        val spot = Spot(id = "spot")
        val testee = Player("Id 2", spots = listOf(spot))

        // When
        val player = testee.updateCreature(creature to creature)

        // Then
        val creaturesOfPlayer = player.spots.mapNotNull { it.creature }
        assertThat(creaturesOfPlayer).doesNotContain(creature)
    }

    @Test
    fun should_find_handcard() {

        // Given
        val card = CreatureMock(id = "Id 1", instanceId = "InstanceId 1")
        val testee = Player("Id 2", handcards = listOf(card))

        // When
        val cardFound = testee.findHandcard("InstanceId 1")

        // Then
        assertThat(cardFound)
                .isNotNull
                .isEqualTo(card)
    }

    @Test
    fun `returns no card`() {

        // Given
        val testee = Player("Id 1")

        // When
        val card = testee.findHandcard("Id 2")

        // Then
        assertThat(card).isNull()
    }
}
