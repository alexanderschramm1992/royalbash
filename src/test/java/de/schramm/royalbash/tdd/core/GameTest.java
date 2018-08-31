package de.schramm.royalbash.tdd.core;

import de.schramm.royalbash.tdd.core.card.NoOpCard;
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
}
