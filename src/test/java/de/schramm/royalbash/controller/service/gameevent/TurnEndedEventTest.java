package de.schramm.royalbash.controller.service.gameevent;

import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TurnEndedEventTest {

    @Test
    public void should_end_players_turn() {

        // Given
        val player1 = Player.builder()
                .id("Id 1")
                .build();
        val player2 = Player.builder().build();
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .playerOnTurn(player1)
                .build();
        val testee = TurnEndedEvent.builder()
                .playerId("Id 1")
                .build();

        // When
        val updatedGame = testee.invoke(game);

        // Then
        assertThat(updatedGame).isNotNull();
        assertThat(updatedGame.getPlayerOnTurn()).isEqualTo(player2);
    }
}