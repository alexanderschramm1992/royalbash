package de.schramm.royalbash.tdd.core;

import de.schramm.royalbash.tdd.core.card.NoOpCard;
import de.schramm.royalbash.tdd.core.creature.NoOpCreature;
import lombok.val;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    @Test
    public void should_deliver_players() {

        // Given
        val player1 = Player.builder().build();
        val player2 = Player.builder().build();
        val testee = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();

        // When
        val players = testee.getPlayers().collect(Collectors.toList());

        // Then
        assertThat(players).contains(player1);
        assertThat(players).contains(player2);
    }

    @Test
    public void should_determine_whos_turn_it_is() {

        // Given
        val player1 = Player.builder().build();
        val player2 = Player.builder().build();
        val testee = Game.builder()
                .playerOnTurn(player1)
                .player1(player1)
                .player2(player2)
                .build();

        // When
        val playerOnTurn = testee.getPlayerOnTurn();

        // Then
        assertThat(playerOnTurn).isEqualTo(player1);
    }

    @Test
    public void should_switch_turn_to_next() {

        // Given
        val player1 = Player.builder().build();
        val player2 = Player.builder().build();
        val testee = Game.builder()
                .player1(player1)
                .player2(player2)
                .playerOnTurn(player1)
                .build();

        // When
        val game = testee.nextTurn();

        // Then
        assertThat(game.getPlayerOnTurn()).isEqualTo(player2);
    }

    @Test
    public void should_switch_turn_to_next_twice() {

        // Given
        val player1 = Player.builder().build();
        val player2 = Player.builder().build();
        val testee = Game.builder()
                .player1(player1)
                .player2(player2)
                .playerOnTurn(player1)
                .build();

        // When
        val game = testee.nextTurn().nextTurn();

        // Then
        assertThat(game.getPlayerOnTurn()).isEqualTo(player1);
    }

    @Test
    public void should_deliver_game_state() {

        // Given
        val testee = Game.builder()
                .state(State.OPEN)
                .build();

        // When
        val state = testee.getState();

        // Then
        assertThat(state).isEqualTo(State.OPEN);
    }

    @Test
    public void should_change_state() {

        // Given
        val testee = Game.builder()
                .state(State.OPEN)
                .build();

        // When
        val game = testee.setState(State.PLAYER_1_WON);

        // Then
        assertThat(game.getState()).isEqualTo(State.PLAYER_1_WON);
    }

    @Test
    public void should_check_win_conditions() {

        // Given
        val testee = Game.builder()
                .state(State.OPEN)
                .player1(Player.builder()
                        .hitpoints(0)
                        .build())
                .player2(Player.builder()
                        .hitpoints(1)
                        .build()
                ).build();

        // When
        val game = testee.updateStateAccordingToWinContitions();

        // Then
        assertThat(game.getState()).isEqualTo(State.PLAYER_2_WON);
    }

    @Test
    public void should_remove_played_card_from_handcards() {

        // Given
        val card = NoOpCard.builder().build();
        val player1 = Player.builder()
                .handcard(card)
                .build();
        val player2 = Player.builder().build();
        val testee = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();

        // When
        val game = testee.playCard(card, player1, player2, Spot.builder().build());

        // Then
        assertThat(game.getPlayer1().getHandcards()).isEmpty();
    }

    @Test
    public void should_remove_dead_creature_after_combat() {

        // Given
        val attacker = NoOpCreature.builder()
                .hitpoints(2)
                .attack(2)
                .build();
        val defender = NoOpCreature.builder()
                .hitpoints(1)
                .attack(1)
                .build();
        val spot1 = Spot.builder()
                .creature(attacker)
                .build();
        val spot2 = Spot.builder()
                .creature(defender)
                .build();
        val player1 = Player.builder()
                .spot(spot1)
                .build();
        val player2 = Player.builder()
                .spot(spot2)
                .build();
        val testee = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();

        // When
        val game = testee.combat(attacker, player1, defender);

        // Then
        val spotsOfPlayer2 = game.getPlayer2().getSpots().collect(Collectors.toList());
        assertThat(spotsOfPlayer2.get(0).getCreature().isPresent()).isFalse();
    }

    @Test
    public void should_deal_damage_to_creature_in_combat() {

        // Given
        val attacker = NoOpCreature.builder()
                .hitpoints(2)
                .attack(2)
                .build();
        val defender = NoOpCreature.builder()
                .hitpoints(1)
                .attack(1)
                .build();
        val spot1 = Spot.builder()
                .creature(attacker)
                .build();
        val spot2 = Spot.builder()
                .creature(defender)
                .build();
        val player1 = Player.builder()
                .spot(spot1)
                .build();
        val player2 = Player.builder()
                .spot(spot2)
                .build();
        val testee = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();

        // When
        val game = testee.combat(attacker, player1, defender);

        // Then
        val spotsOfPlayer1 = game.getPlayer1().getSpots().collect(Collectors.toList());
        assertThat(spotsOfPlayer1.get(0).getCreature().isPresent()).isTrue();
        assertThat(spotsOfPlayer1.get(0).getCreature().get().getHitpoints()).isEqualTo(1);
    }

    @Test
    public void should_deal_damage_to_player_in_combat() {

        // Given
        val creature = NoOpCreature.builder()
                .attack(5)
                .build();
        val spot = Spot.builder()
                .creature(creature)
                .build();
        val player1 = Player.builder()
                .spot(spot)
                .build();
        val player2 = Player.builder()
                .hitpoints(20)
                .build();
        val testee = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();

        // When
        val game = testee.combat(creature, player1);

        // Then
        assertThat(game.getPlayer2().getHitpoints()).isEqualTo(15);
    }

    @Test
    public void should_set_game_state_when_player_dies_in_combat() {

        // Given
        val creature = NoOpCreature.builder()
                .attack(5)
                .build();
        val spot = Spot.builder()
                .creature(creature)
                .build();
        val player1 = Player.builder()
                .hitpoints(1)
                .spot(spot)
                .build();
        val player2 = Player.builder()
                .hitpoints(5)
                .build();
        val testee = Game.builder()
                .state(State.OPEN)
                .player1(player1)
                .player2(player2)
                .build();

        // When
        val game = testee.combat(creature, player1);

        // Then
        assertThat(game.getState()).isEqualTo(State.PLAYER_1_WON);
    }
}
