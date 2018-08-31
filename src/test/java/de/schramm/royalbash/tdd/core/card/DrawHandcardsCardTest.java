package de.schramm.royalbash.tdd.core.card;

import de.schramm.royalbash.tdd.core.Context;
import de.schramm.royalbash.tdd.core.Game;
import de.schramm.royalbash.tdd.core.Player;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DrawHandcardsCardTest {

    @Test
    public void should_draw_handcards_for_target_player() {

        // Given
        val testee = DrawHandcardsCard.builder()
                .amountOfCards(2)
                .build();
        val player1 = Player.builder()
                .handcard(testee)
                .deckcard(NoOpCard.builder().build())
                .deckcard(NoOpCard.builder().build())
                .build();
        val player2 = Player.builder().build();
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();
        val context = Context.builder()
                .game(game)
                .targetPlayer(player1)
                .build();

        // When
        val updatedGame = testee.invoke(context);

        // Then
        assertThat(updatedGame.getPlayer1().getHandcards()).hasSize(3);
        assertThat(updatedGame.getPlayer1().getHandcards()).contains(NoOpCard.builder().build());
    }

    @Test
    public void should_do_nothing_if_target_player_has_no_deckcards() {

        // Given
        val testee = DrawHandcardsCard.builder()
                .amountOfCards(2)
                .build();
        val player1 = Player.builder()
                .handcard(testee)
                .build();
        val player2 = Player.builder().build();
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();
        val context = Context.builder()
                .game(game)
                .targetPlayer(player1)
                .build();

        // When
        val updatedGame = testee.invoke(context);

        // Then
        assertThat(updatedGame.getPlayer1().getHandcards()).hasSize(1);
    }
}