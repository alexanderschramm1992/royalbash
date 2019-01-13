package de.schramm.royalbash.domain

import de.schramm.royalbash.domain.State.*
import de.schramm.royalbash.domain.card.CardMock
import de.schramm.royalbash.domain.card.creature.CreatureMock
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class GameTest {

    @Test
    fun `delivers players`() {

        // Given
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val testee = Game("Id 3", player1 = player1, player2 = player2)

        // When
        val players = testee.players

        // Then
        Assertions.assertThat(players)
                .contains(player1)
                .contains(player2)
    }

    @Test
    fun `determines who's turn it is`() {

        // Given
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val testee = Game("Id 3", player1 = player1, player2 = player2, playerOnTurn = player1)

        // When
        val playerOnTurn = testee.playerOnTurn

        // Then
        assertThat(playerOnTurn).isEqualTo(player1)
    }

    @Test
    fun `switches to next turn`() {

        // Given
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val testee = Game("Id 3", player1 = player1, player2 = player2, playerOnTurn = player1)

        // When
        val game = testee.nextTurn()

        // Then
        assertThat(game.playerOnTurn).isEqualTo(player2)
    }

    @Test
    fun `switches to next turn twice`() {

        // Given
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val testee = Game("Id 3", player1 = player1, player2 = player2, playerOnTurn = player1)

        // When
        val game = testee.nextTurn().nextTurn()

        // Then
        assertThat(game.playerOnTurn).isEqualTo(player1)
    }

    @Test
    fun `delivers game state`() {

        // Given
        val testee = Game("Id 1", OPEN, Player("Id 2"), Player("Id 3"))

        // When
        val state = testee.state

        // Then
        Assertions.assertThat(state).isEqualTo(OPEN)
    }

    @Test
    fun `changes game state`() {

        // Given
        val testee = Game("Id 1", OPEN, Player("Id 2"), Player("Id 3"))

        // When
        val game = testee.setState(PLAYER_1_WON)

        // Then
        assertThat(game.state).isEqualTo(PLAYER_1_WON)
    }

    @Test
    fun `checks win condition`() {

        // Given
        val testee = Game(
                "Id 1",
                OPEN,
                Player("Id 2", hitpoints = 0),
                Player("Id 3", hitpoints = 1))

        // When
        val game = testee.updateStateAccordingToWinContitions()

        // Then
        assertThat(game.state).isEqualTo(PLAYER_2_WON)
    }

    @Test
    fun `removes played card from handcards after played on player`() {

        // Given
        val card = CardMock("Id 1")
        val player1 = Player("Id 2", handcards = listOf(card))
        val player2 = Player("Id 3")
        val testee = Game("Id 4", player1 = player1, player2 = player2)

        // When
        val game = testee.playCard(card, player1, player2)

        // Then
        assertThat(game.player1.handcards).isEmpty()
    }

    @Test
    fun `removes played card from handcards after played on spot`() {

        // Given
        val card = CardMock("Id 1")
        val player1 = Player("Id 2", handcards = listOf(card))
        val player2 = Player("Id 3")
        val spot = Spot("Id 4")
        val testee = Game("Id 5", player1 = player1, player2 = player2)

        // When
        val game = testee.playCard(card, player1, spot)

        // Then
        assertThat(game.player1.handcards).isEmpty()
    }

    @Test
    fun `removes dead creature after combat`() {

        // Given
        val attacker = CreatureMock(
                "Id 1",
                hitpoints = 2,
                attack = 2)
        val defender = CreatureMock(
                "Id 2",
                hitpoints = 1,
                attack = 1)
        val spot1 = Spot(id = "spot1", creature = attacker)
        val spot2 = Spot(id = "spot2", creature = defender)
        val player1 = Player("Id 3", spots = listOf(spot1))
        val player2 = Player("Id 4", spots = listOf(spot2))
        val testee = Game("Id 5", player1 = player1, player2 = player2)

        // When
        val game = testee.combat(attacker, player1, defender)

        // Then
        val spotsOfPlayer2 = game.player2.spots
        assertThat(spotsOfPlayer2[0].getCreature()).isNotPresent
    }

    @Test
    fun `deals damage to creature in combat`() {

        // Given
        val attacker = CreatureMock(
                "Id 1",
                hitpoints = 2,
                attack = 2)
        val defender = CreatureMock(
                "Id 2",
                hitpoints = 1,
                attack = 1)
        val spot1 = Spot(id = "spot1", creature = attacker)
        val spot2 = Spot(id = "spot2", creature = defender)
        val player1 = Player("Id 3", spots = listOf(spot1))
        val player2 = Player("Id 4", spots = listOf(spot2))
        val testee = Game("Id 5", player1 = player1, player2 = player2)

        // When
        val game = testee.combat(attacker, player1, defender)

        // Then
        val spotsOfPlayer1 = game.player1.spots
        assertThat(spotsOfPlayer1[0].getCreature())
                .isPresent
                .map { it.hitpoints }
                .isEqualTo(Optional.of(1))
    }

    @Test
    fun `deals damage to player in combat`() {

        // Given
        val creature = CreatureMock("Id 1", attack = 5)
        val spot = Spot(id = "spot", creature = creature)
        val player1 = Player("Id 2", spots = listOf(spot))
        val player2 = Player("Id 3", hitpoints = 20)
        val testee = Game("Id 4", player1 = player1, player2 = player2)

        // When
        val game = testee.combat(creature, player1)

        // Then
        assertThat(game.player2.hitpoints).isEqualTo(15)
    }

    @Test
    fun `sets game state when player dies in combat`() {

        // Given
        val creature = CreatureMock("Id 1", attack = 5)
        val spot = Spot(id = "spot", creature = creature)
        val player1 = Player("Id 2", hitpoints = 1, spots = listOf(spot))
        val player2 = Player("Id 3", hitpoints = 5)
        val testee = Game("Id 4", OPEN, player1 = player1, player2 = player2)

        // When
        val game = testee.combat(creature, player1)

        // Then
        assertThat(game.state).isEqualTo(PLAYER_1_WON)
    }

    @Test
    fun `delivers player with given id`() {

        // Given
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val testee = Game("Id 3", player1 = player1, player2 = player2)

        // When
        val playerOptional = testee.findPlayer("Id 1")

        // Then
        assertThat(playerOptional)
                .isPresent
                .isEqualTo(Optional.of(player1))
    }

    @Test
    fun `delivers optional empty if player id cannot be found`() {

        // Given
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val testee = Game("Id 3", player1 = player1, player2 = player2)

        // When
        val optional = testee.findPlayer("Id 4")

        // Then
        assertThat(optional.isPresent).isFalse()
    }

    @Test
    fun `delivers creature with given id`() {

        // Given
        val creature = CreatureMock("Id 1")
        val spot = Spot(id = "spot", creature = creature)
        val player1 = Player("Id 2", spots = listOf(spot))
        val player2 = Player("Id 3")
        val testee = Game("Id 4", player1 = player1, player2 = player2)

        // When
        val optional = testee.findCreature("Id 1")

        // Then
        assertThat(optional)
                .isPresent
                .isEqualTo(Optional.of(creature))
    }

    @Test
    fun `delivers optional empty if creature id cannot be found`() {

        // Given
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val testee = Game("Id 3", player1 = player1, player2 = player2)

        // When
        val optional = testee.findCreature("Id 1")

        // Then
        assertThat(optional.isPresent).isFalse()
    }

    @Test
    fun `delivers spot with given id`() {

        // Given
        val spot = Spot(id = "Id 1")
        val player1 = Player("Id 2", spots = listOf(spot))
        val player2 = Player("Id 3")
        val testee = Game("Id 4", player1 = player1, player2 = player2)

        // When
        val optional = testee.findSpot("Id 1")

        // Then
        assertThat(optional)
                .isPresent
                .isEqualTo(Optional.of(spot))
    }

    @Test
    fun `delivers optional empty if spot id cannot be found`() {

        // Given
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val testee = Game("Id 3", player1 = player1, player2 = player2)

        // When
        val optional = testee.findSpot("Id 1")

        // Then
        assertThat(optional.isPresent).isFalse()
    }
}
