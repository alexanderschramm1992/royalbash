package de.schramm.royalbash.domain

import de.schramm.royalbash.domain.State.*
import de.schramm.royalbash.domain.card.creature.NoOpCreature
import de.schramm.royalbash.domain.card.spell.NoOpSpell
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameTest {

    @Test
    fun `returns players`() {

        // Given
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val testee = Game("Id 3", player1 = player1, player2 = player2)

        // When
        val players = testee.players

        // Then
        assertThat(players)
                .contains(player1)
                .contains(player2)
    }

    @Test
    fun `returns who's turn it is`() {

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
    fun `returns game state`() {

        // Given
        val testee = Game("Id 1", OPEN, Player("Id 2"), Player("Id 3"))

        // When
        val state = testee.state

        // Then
        assertThat(state).isEqualTo(OPEN)
    }

    @Test
    fun `changes game state`() {

        // Given
        val testee = Game("Id 1", OPEN, Player("Id 2"), Player("Id 3"))

        // When
        val game = testee.withState(PLAYER_1_WON)

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
        val game = testee.updateStateAccordingToWinConditions()

        // Then
        assertThat(game.state).isEqualTo(PLAYER_2_WON)
    }

    @Test
    fun `removes played card from handcards after played on player`() {

        // Given
        val card = NoOpSpell(id = "Id 1", instanceId = "InstanceId 1")
        val player1 = Player("Id 2", handcards = listOf(card))
        val player2 = Player("Id 3")
        val testee = Game("Id 4", player1 = player1, player2 = player2)

        // When
        val game = card.invoke(InvokationOnPlayerContext(UUIDGeneratorMock, testee, player1, player2))

        // Then
        assertThat(game.player1.handcards).isEmpty()
    }

    @Test
    fun `removes played card from handcards after played on spot`() {

        // Given
        val card = NoOpSpell(id = "Id 1", instanceId = "InstanceId 1")
        val player1 = Player("Id 2", handcards = listOf(card))
        val player2 = Player("Id 3")
        val spot = Spot("Id 4")
        val testee = Game("Id 5", player1 = player1, player2 = player2)

        // When
        val game = card.invoke(InvokationOnSpotContext(UUIDGeneratorMock, testee, player1, spot))

        // Then
        assertThat(game.player1.handcards).isEmpty()
    }

    @Test
    fun `removes dead creature after combat`() {

        // Given
        val attacker = NoOpCreature(
                id = "Id 1",
                instanceId = "Instance Id 1",
                hitpoints = 2,
                attack = 2)
        val defender = NoOpCreature(
                id = "Id 2",
                instanceId = "Instance Id 2",
                hitpoints = 1,
                attack = 1)
        val spot1 = Spot(id = "spot1", creature = attacker)
        val spot2 = Spot(id = "spot2", creature = defender)
        val player1 = Player("Id 3", spots = listOf(spot1))
        val player2 = Player("Id 4", spots = listOf(spot2))
        val testee = Game("Id 5", player1 = player1, player2 = player2)

        // When
        val game = attacker.attack(AttackCreatureContext(UUIDGeneratorMock, testee, player1, defender))

        // Then
        val spotsOfPlayer2 = game.player2.spots
        assertThat(spotsOfPlayer2[0].creature).isNull()
    }

    @Test
    fun `deals damage to creature in combat`() {

        // Given
        val attacker = NoOpCreature(
                id = "Id 1",
                instanceId = "InstanceId 1",
                hitpoints = 2,
                attack = 2)
        val defender = NoOpCreature(
                id = "Id 2",
                instanceId = "InstanceId 2",
                hitpoints = 1,
                attack = 1)
        val spot1 = Spot(id = "spot1", creature = attacker)
        val spot2 = Spot(id = "spot2", creature = defender)
        val player1 = Player("Id 3", spots = listOf(spot1))
        val player2 = Player("Id 4", spots = listOf(spot2))
        val testee = Game("Id 5", player1 = player1, player2 = player2)

        // When
        val game = attacker.attack(AttackCreatureContext(UUIDGeneratorMock, testee, player1, defender))

        // Then
        val spotsOfPlayer1 = game.player1.spots
        assertThat(spotsOfPlayer1[0].creature)
                .isNotNull
                .extracting { it?.hitpoints }
                .isEqualTo(1)
    }

    @Test
    fun `deals damage to player in combat`() {

        // Given
        val creature = NoOpCreature(id = "Id 1",
                                    instanceId = "InstanceId 1",
                                    attack = 5)
        val spot = Spot(id = "spot", creature = creature)
        val player1 = Player("Id 2", spots = listOf(spot))
        val player2 = Player("Id 3", hitpoints = 20)
        val testee = Game("Id 4", player1 = player1, player2 = player2)

        // When
        val game = creature.attack(AttackPlayerContext(UUIDGeneratorMock, testee, player1, player2))

        // Then
        assertThat(game.player2.hitpoints).isEqualTo(15)
    }

    @Test
    fun `sets game state when player dies in combat`() {

        // Given
        val creature = NoOpCreature(id = "Id 1", instanceId = "InstanceId1", attack = 5)
        val spot = Spot(id = "spot", creature = creature)
        val player1 = Player("Id 2", hitpoints = 1, spots = listOf(spot))
        val player2 = Player("Id 3", hitpoints = 5)
        val testee = Game("Id 4", OPEN, player1 = player1, player2 = player2)

        // When
        val game = creature.attack(AttackPlayerContext(UUIDGeneratorMock, testee, player1, player2))

        // Then
        assertThat(game.state).isEqualTo(PLAYER_1_WON)
    }

    @Test
    fun `returns player with given id`() {

        // Given
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val testee = Game("Id 3", player1 = player1, player2 = player2)

        // When
        val player = testee.findPlayer("Id 1")

        // Then
        assertThat(player)
                .isNotNull
                .isEqualTo(player1)
    }

    @Test
    fun `returns optional empty if player id cannot be found`() {

        // Given
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val testee = Game("Id 3", player1 = player1, player2 = player2)

        // When
        val player = testee.findPlayer("Id 4")

        // Then
        assertThat(player).isNull()
    }

    @Test
    fun `returns creature with given id`() {

        // Given
        val creature = NoOpCreature(id = "Id 1", instanceId = "InstanceId 1")
        val spot = Spot(id = "spot", creature = creature)
        val player1 = Player("Id 2", spots = listOf(spot))
        val player2 = Player("Id 3")
        val testee = Game("Id 4", player1 = player1, player2 = player2)

        // When
        val actualCreature = testee.findCreature(creature.instanceId)

        // Then
        assertThat(actualCreature)
                .isNotNull
                .isEqualTo(creature)
    }

    @Test
    fun `returns optional empty if creature id cannot be found`() {

        // Given
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val testee = Game("Id 3", player1 = player1, player2 = player2)

        // When
        val creature = testee.findCreature("Id 1")

        // Then
        assertThat(creature).isNull()
    }

    @Test
    fun `returns spot with given id`() {

        // Given
        val spot = Spot(id = "Id 1")
        val player1 = Player("Id 2", spots = listOf(spot))
        val player2 = Player("Id 3")
        val testee = Game("Id 4", player1 = player1, player2 = player2)

        // When
        val maybeSpot = testee.findSpot("Id 1")

        // Then
        assertThat(maybeSpot).isEqualTo(spot)
    }

    @Test
    fun `returns null if spot id cannot be found`() {

        // Given
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val testee = Game("Id 3", player1 = player1, player2 = player2)

        // When
        val maybeSpot = testee.findSpot("Id 1")

        // Then
        assertThat(maybeSpot).isNull()
    }

    @Test
    fun `logs event`() {

        // Given
        val player1 = Player("Id 1")
        val player2 = Player("Id 2")
        val testee = Game("Id 3", player1 = player1, player2 = player2)

        val log = Log("Id 4", "Something has happened")

        // When
        val game = testee.log(log)

        // Then
        assertThat(game.logs).containsExactly(log)
    }
}
