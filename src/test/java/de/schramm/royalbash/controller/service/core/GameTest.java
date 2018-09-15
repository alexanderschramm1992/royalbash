package de.schramm.royalbash.controller.service.core;

import de.schramm.royalbash.controller.service.core.card.NoOpCard;
import de.schramm.royalbash.controller.service.core.creature.NoOpCreature;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        Assertions.assertThat(players).contains(player1);
        Assertions.assertThat(players).contains(player2);
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
        Assertions.assertThat(state).isEqualTo(State.OPEN);
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
        assertThat(game.getPlayer1().getHandcards().collect(Collectors.toList())).isEmpty();
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

    @Test
    public void should_deliver_player_with_given_id() throws Exception {

        // Given
        val player1 = Player.builder()
                .id("Id 1")
                .build();
        val testee = Game.builder()
                .player1(player1)
                .player2(Player.builder().build())
                .build();

        // When
        val playerOptional = testee.findPlayer("Id 1");

        // Then
        val player = playerOptional.orElseThrow(() -> new Exception("Player is not present"));
        assertThat(player).isEqualTo(player1);
    }

    @Test
    public void should_deliver_optional_empty_if_player_id_cannot_be_found() {

        // Given
        val testee = Game.builder()
                .player1(Player.builder().build())
                .player2(Player.builder().build())
                .build();

        // When
        val optional = testee.findPlayer("Id 1");

        // Then
        assertThat(optional.isPresent()).isFalse();
    }

    @Test
    public void should_deliver_creature_with_given_id() throws Exception {

        // Given
        val creature = mock(Creature.class);
        when(creature.getId()).thenReturn("Id 1");
        val testee = Game.builder()
                .player1(Player.builder()
                        .spot(Spot.builder()
                            .creature(creature)
                            .build())
                        .build())
                .player2(Player.builder().build())
                .build();

        // When
        val optional = testee.findCreature("Id 1");

        // Then
        val foundCreature = optional.orElseThrow(() -> new Exception("Creature not present"));
        assertThat(foundCreature).isEqualTo(creature);
    }

    @Test
    public void should_deliver_optional_empty_if_creature_id_cannot_be_found() {

        // Given
        val testee = Game.builder()
                .player1(Player.builder().build())
                .player2(Player.builder().build())
                .build();

        // When
        val optional = testee.findCreature("Id 1");

        // Then
        assertThat(optional.isPresent()).isFalse();
    }
}
