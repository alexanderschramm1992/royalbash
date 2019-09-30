package de.schramm.royalbash.application

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.infrastructure.RandomUUIDGenerator
import de.schramm.royalbash.infrastructure.controller.gameevent.GameEventDTO
import de.schramm.royalbash.infrastructure.database.GamePersistenceMapper
import de.schramm.royalbash.infrastructure.database.InMemoryGamePersistenceOperations
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class GameServiceTest {

    @Test
    fun `retrieves stored game`() {

        // Given
        val gameId = "Game Id"
        val game = Game(gameId,
                        player1 = Player("Id 1"),
                        player2 = Player("Id 2"))
        val repository = mockk<Games> {
            every { findAll() } returns setOf(game)
        }
        val testee = GameService(RandomUUIDGenerator, repository)

        // When
        val retrievedGame = testee.retrieveGame(gameId)

        // Then
        assertThat(retrievedGame).isEqualTo(game.toExternalModel())
    }

    @Test
    fun `retrieves stored games`() {

        // Given
        val game = Game("Game Id",
                        player1 = Player("Id 1"),
                        player2 = Player("Id 2"))
        val repository = mockk<Games> {
            every { findAll() } returns setOf(game)
        }
        val testee = GameService(RandomUUIDGenerator, repository)

        // When
        val retrievedGame = testee.retrieveGames()

        // Then
        assertThat(retrievedGame).containsExactly(game.toExternalModel())
    }

    @Test
    fun `creates and stores game`() {

        // Given
        val accountId1 = "Account 1"
        val accountId2 = "Account 2"
        val id = UUID.randomUUID().toString()
        val repository = GamePersistenceMapper(InMemoryGamePersistenceOperations())
        val uuidGenerator = mockk<UUIDGenerator> {
            every { generateId() } returns id
        }
        val testee = GameService(uuidGenerator, repository)

        // When
        val game = testee.createGame(accountId1, accountId2)

        // Then
        assertThat(game).isNotNull
        assertThat(game.player1.name).isEqualTo(accountId1)
        assertThat(game.player2.name).isEqualTo(accountId2)
        assertThat(repository.findById(id)).isNotNull
    }

    @Test
    fun `retrieves game after event is committed`() {

        // Given
        val gameId = "Id 1"
        val game = Game(gameId,
                        player1 = Player("Id 2"),
                        player2 = Player("Id 3"))
        val gameEvent = mockk<GameEventDTO> {
            every { this@mockk.invoke(game) } returns game
        }
        val repository = mockk<Games> {
            every { findAll() } returns setOf(game)
        }
        val testee = GameService(RandomUUIDGenerator, repository)

        // When
        val updatedGame = testee.commitGameEvent(gameId, gameEvent)

        // Then
        assertThat(updatedGame).isEqualTo(game.toExternalModel())
    }
}
