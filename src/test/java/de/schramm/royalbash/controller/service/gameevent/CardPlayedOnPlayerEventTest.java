package de.schramm.royalbash.controller.service.gameevent;

import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import de.schramm.royalbash.controller.service.core.card.NoOpCard;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardPlayedOnPlayerEventTest {

    @Test
    public void should_invoke_play_card() {

        // Given
        val testee = CardPlayedOnPlayerEvent.builder()
                .cardId("Id 1")
                .ownerId("Id 2")
                .targetPlayerId("Id 2")
                .build();
        val card = NoOpCard.builder()
                .id("Id 1")
                .build();
        val game = Game.builder()
                .player1(Player.builder()
                        .id("Id 2")
                        .handcard(card)
                        .build())
                .player2(Player.builder().build())
                .build();

        // When
        val updatedGame = testee.invoke(game);

        // Then
        assertThat(updatedGame).isNotNull();
        assertThat(updatedGame.getPlayer1().getHandcards().count()).isEqualTo(0);
    }
}
