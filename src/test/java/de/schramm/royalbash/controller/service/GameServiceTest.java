package de.schramm.royalbash.controller.service;

import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.gameevent.GameEvent;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GameServiceTest {

    @Test
    public void should_retrieve_stored_game() {

        // Given
        val gameId = "Game Id";
        val game = Game.builder().build();
        val repository = mock(GameRepository.class);
        when(repository.findOne(gameId)).thenReturn(game);
        val testee = GameService.builder()
                .gameRepository(repository)
                .build();

        // When
        val retrievedGame = testee.retrieveGame(gameId);

        // Then
        assertThat(retrievedGame).isEqualTo(game);
    }

    @Test
    public void should_create_and_store_game() {

        // Given
        val accountId1 = "Account 1";
        val accountId2 = "Account 2";
        val repository = mock(GameRepository.class);
        val testee = GameService.builder()
                .gameRepository(repository)
                .build();

        // When
        val game = testee.createGame(accountId1, accountId2);

        // Then
        assertThat(game).isNotNull();
        assertThat(game.getPlayer1().getName()).isEqualTo(accountId1);
        assertThat(game.getPlayer2().getName()).isEqualTo(accountId2);
        verify(repository, times(1)).save(game);
    }

    @Test
    public void should_retrieve_game_after_event_is_committed() {

        // Given
        val gameEvent = mock(GameEvent.class);
        val repository = mock(GameRepository.class);
        val testee = GameService.builder()
                .gameRepository(repository)
                .build();

        // When
        val game = testee.commitGameEvent(gameEvent);

        // Then
        assertThat(game).isNotNull();
        assertThat(game).isEqualTo(game);
    }
}