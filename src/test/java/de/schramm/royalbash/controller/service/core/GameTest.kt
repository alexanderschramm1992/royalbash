package de.schramm.royalbash.controller.service.core

import de.schramm.royalbash.controller.service.core.card.NoOpCard
import de.schramm.royalbash.controller.service.core.card.creature.CreatureMock
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.*
import java.util.stream.Collectors

class GameTest {

    @Test
    fun should_deliver_players() {

        // Given
        val player1 = Player.builder().build()
        val player2 = Player.builder().build()
        val testee = Game.builder()
                .player1(player1)
                .player2(player2)
                .build()

        // When
        val players = testee.players.collect(Collectors.toList())

        // Then
        Assertions.assertThat(players)
                .contains(player1)
                .contains(player2)
    }

    @Test
    fun should_determine_whos_turn_it_is() {

        // Given
        val player1 = Player.builder().build()
        val player2 = Player.builder().build()
        val testee = Game.builder()
                .playerOnTurn(player1)
                .player1(player1)
                .player2(player2)
                .build()

        // When
        val playerOnTurn = testee.playerOnTurn

        // Then
        assertThat(playerOnTurn).isEqualTo(player1)
    }

    @Test
    fun should_switch_turn_to_next() {

        // Given
        val player1 = Player.builder().build()
        val player2 = Player.builder().build()
        val testee = Game.builder()
                .player1(player1)
                .player2(player2)
                .playerOnTurn(player1)
                .build()

        // When
        val game = testee.nextTurn()

        // Then
        assertThat(game.playerOnTurn).isEqualTo(player2)
    }

    @Test
    fun should_switch_turn_to_next_twice() {

        // Given
        val player1 = Player.builder().build()
        val player2 = Player.builder().build()
        val testee = Game.builder()
                .player1(player1)
                .player2(player2)
                .playerOnTurn(player1)
                .build()

        // When
        val game = testee.nextTurn().nextTurn()

        // Then
        assertThat(game.playerOnTurn).isEqualTo(player1)
    }

    @Test
    fun should_deliver_game_state() {

        // Given
        val testee = Game.builder()
                .state(State.OPEN)
                .build()

        // When
        val state = testee.state

        // Then
        Assertions.assertThat(state).isEqualTo(State.OPEN)
    }

    @Test
    fun should_change_state() {

        // Given
        val testee = Game.builder()
                .state(State.OPEN)
                .build()

        // When
        val game = testee.setState(State.PLAYER_1_WON)

        // Then
        assertThat(game.state).isEqualTo(State.PLAYER_1_WON)
    }

    @Test
    fun should_check_win_conditions() {

        // Given
        val testee = Game.builder()
                .state(State.OPEN)
                .player1(Player.builder()
                        .hitpoints(0)
                        .build())
                .player2(Player.builder()
                        .hitpoints(1)
                        .build()
                ).build()

        // When
        val game = testee.updateStateAccordingToWinContitions()

        // Then
        assertThat(game.state).isEqualTo(State.PLAYER_2_WON)
    }

    @Test
    fun should_remove_played_card_from_handcards() {

        // Given
        val card = NoOpCard.builder().build()
        val player1 = Player.builder()
                .handcard(card)
                .build()
        val player2 = Player.builder().build()
        val testee = Game.builder()
                .player1(player1)
                .player2(player2)
                .build()

        // When
        val game = testee.playCard(card, player1, player2)

        // Then
        assertThat(game.player1.handcards).isEmpty()
    }

    @Test
    fun should_remove_dead_creature_after_combat() {

        // Given
        val attacker = CreatureMock(
                "Id 1",
                hitpoints = 2,
                attack = 2
        )
        val defender = CreatureMock(
                "Id 1",
                hitpoints = 1,
                attack = 1
        )
        val spot1 = Spot.builder()
                .creature(attacker)
                .build()
        val spot2 = Spot.builder()
                .creature(defender)
                .build()
        val player1 = Player.builder()
                .spot(spot1)
                .build()
        val player2 = Player.builder()
                .spot(spot2)
                .build()
        val testee = Game.builder()
                .player1(player1)
                .player2(player2)
                .build()

        // When
        val game = testee.combat(attacker, player1, defender)

        // Then
        val spotsOfPlayer2 = game.player2.spots.collect(Collectors.toList())
        assertThat(spotsOfPlayer2[0].creature).isNotPresent
    }

    @Test
    fun should_deal_damage_to_creature_in_combat() {

        // Given
        val attacker = CreatureMock(
                "Id 1",
                hitpoints = 2,
                attack = 2
        )
        val defender = CreatureMock(
                "Id 1",
                hitpoints = 1,
                attack = 1
        )
        val spot1 = Spot.builder()
                .creature(attacker)
                .build()
        val spot2 = Spot.builder()
                .creature(defender)
                .build()
        val player1 = Player.builder()
                .spot(spot1)
                .build()
        val player2 = Player.builder()
                .spot(spot2)
                .build()
        val testee = Game.builder()
                .player1(player1)
                .player2(player2)
                .build()

        // When
        val game = testee.combat(attacker, player1, defender)

        // Then
        val spotsOfPlayer1 = game.player1.spots.collect(Collectors.toList())
        assertThat(spotsOfPlayer1[0].creature)
                .isPresent
                .map { it.hitpoints }
                .isEqualTo(Optional.of(1))
    }

    @Test
    fun should_deal_damage_to_player_in_combat() {

        // Given
        val creature = CreatureMock(
                "Id 1",
                attack = 5
        )
        val spot = Spot.builder()
                .creature(creature)
                .build()
        val player1 = Player.builder()
                .spot(spot)
                .build()
        val player2 = Player.builder()
                .hitpoints(20)
                .build()
        val testee = Game.builder()
                .player1(player1)
                .player2(player2)
                .build()

        // When
        val game = testee.combat(creature, player1)

        // Then
        assertThat(game.player2.hitpoints).isEqualTo(15)
    }

    @Test
    fun should_set_game_state_when_player_dies_in_combat() {

        // Given
        val creature = CreatureMock(
                "Id 1",
                attack = 5
        )
        val spot = Spot.builder()
                .creature(creature)
                .build()
        val player1 = Player.builder()
                .hitpoints(1)
                .spot(spot)
                .build()
        val player2 = Player.builder()
                .hitpoints(5)
                .build()
        val testee = Game.builder()
                .state(State.OPEN)
                .player1(player1)
                .player2(player2)
                .build()

        // When
        val game = testee.combat(creature, player1)

        // Then
        assertThat(game.state).isEqualTo(State.PLAYER_1_WON)
    }

    @Test
    @Throws(Exception::class)
    fun should_deliver_player_with_given_id() {

        // Given
        val player1 = Player.builder()
                .id("Id 1")
                .build()
        val testee = Game.builder()
                .player1(player1)
                .player2(Player.builder().build())
                .build()

        // When
        val playerOptional = testee.findPlayer("Id 1")

        // Then
        val player = playerOptional.orElseThrow { Exception("Player is not present") }
        assertThat(player).isEqualTo(player1)
    }

    @Test
    fun should_deliver_optional_empty_if_player_id_cannot_be_found() {

        // Given
        val testee = Game.builder()
                .player1(Player.builder().build())
                .player2(Player.builder().build())
                .build()

        // When
        val optional = testee.findPlayer("Id 1")

        // Then
        assertThat(optional.isPresent).isFalse()
    }

    @Test
    @Throws(Exception::class)
    fun should_deliver_creature_with_given_id() {

        // Given
        val creature = mock(Creature::class.java)
        `when`(creature.id).thenReturn("Id 1")
        val testee = Game.builder()
                .player1(Player.builder()
                        .spot(Spot.builder()
                                .creature(creature)
                                .build())
                        .build())
                .player2(Player.builder().build())
                .build()

        // When
        val optional = testee.findCreature("Id 1")

        // Then
        val foundCreature = optional.orElseThrow { Exception("Creature not present") }
        assertThat(foundCreature).isEqualTo(creature)
    }

    @Test
    fun should_deliver_optional_empty_if_creature_id_cannot_be_found() {

        // Given
        val testee = Game.builder()
                .player1(Player.builder().build())
                .player2(Player.builder().build())
                .build()

        // When
        val optional = testee.findCreature("Id 1")

        // Then
        assertThat(optional.isPresent).isFalse()
    }
}
