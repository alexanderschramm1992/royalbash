package de.schramm.royalbash.tdd.core.creature;

import de.schramm.royalbash.tdd.core.Context;
import de.schramm.royalbash.tdd.core.Game;
import de.schramm.royalbash.tdd.core.Player;
import de.schramm.royalbash.tdd.core.Spot;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NoOpCreatureTest {

    @Test
    public void should_put_creature_to_player_board() {

        // Given
        val testee = NoOpCreature.builder()
                .build();
        val spot = Spot.builder().build();
        val player1 = Player.builder()
                .handcard(testee)
                .spot(spot)
                .build();
        val player2 = Player.builder().build();
        val game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();
        val context = Context.builder()
                .game(game)
                .owner(player1)
                .targetSpot(spot)
                .build();

        // When
        val updatedGame = testee.invoke(context);

        // Then
        val cardInSpot = updatedGame.getPlayer1().getSpots().get(0).getCard();
        assertThat(cardInSpot.isPresent()).isTrue();
        assertThat(cardInSpot.orElse(NoOpCreature.builder().build())).isEqualTo(testee);
    }
}