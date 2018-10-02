package de.schramm.royalbash.controller.service;

import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.gameevent.GameEvent;
import lombok.val;
import org.junit.Test;

import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GameServiceTest {

    @Test
    public void should_retrieve_stored_game() {

        // Given
        val gameId = "Game Id";
        val game = Game.builder()
                .id(gameId)
                .build();
        val repository = mock(GameRepository.class);
        when(repository.findAll()).thenReturn(Collections.singleton(game));
        val testee = GameService.builder()
                .gameRepository(repository)
                .build();

        // When
        val retrievedGame = testee.retrieveGame(gameId);

        // Then
        assertThat(retrievedGame)
                .isPresent()
                .hasValue(game);
    }

    @Test
    public void should_create_and_store_game() {

        // Given
        val accountId1 = "Account 1";
        val accountId2 = "Account 2";
        val repository = mock(GameRepository.class);
        val uuidGenerator = mock(UUIDGenerator.class);
        when(uuidGenerator.generateUUID()).thenReturn(UUID.randomUUID());
        val testee = GameService.builder()
                .gameRepository(repository)
                .uuidGenerator(uuidGenerator)
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
        val game = Game.builder().build();
        val gameEvent = mock(GameEvent.class);
        when(gameEvent.invoke(game)).thenReturn(game);
        val repository = mock(GameRepository.class);
        when(repository.findOne("Id 1")).thenReturn(game);
        val testee = GameService.builder()
                .gameRepository(repository)
                .build();

        // When
        val updatedGame = testee.commitGameEvent("Id 1", gameEvent);

        // Then
        assertThat(updatedGame).isNotNull();
        assertThat(updatedGame).isEqualTo(game);
    }
}