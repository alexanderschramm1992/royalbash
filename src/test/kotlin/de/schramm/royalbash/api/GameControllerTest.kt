package de.schramm.royalbash.api

import de.schramm.royalbash.application.GameService
import de.schramm.royalbash.application.gameevent.NoOpEventDTO
import de.schramm.royalbash.application.toExternalModel
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.State.OPEN
import de.schramm.royalbash.domain.Turn
import de.schramm.royalbash.infrastructure.JacksonConfig
import de.schramm.royalbash.infrastructure.controller.GameController
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@Import(JacksonConfig::class)
@WebMvcTest(GameController::class, secure = false)
class GameControllerTest {

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun gameService() = mockk<GameService>()
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var gameService: GameService

    @Test
    fun `delivers game`() {

        // Given
        val game = Game(
                "1",
                player1 = Player("Id 2"),
                player2 = Player("Id 3"),
                turns = listOf(Turn(Player("Id 3"))),
                state = OPEN)
        every { gameService.retrieveGame("1") } returns game.toExternalModel()

        val requestBuilder = MockMvcRequestBuilders
                .get("/game/1")
                .accept(MediaType.APPLICATION_JSON)

        // When
        val result = mockMvc.perform(requestBuilder)
                .andReturn()
                .response
                .contentAsString

        // Then
        JSONAssert.assertEquals("""{
                "id": "1",
                "player1": {"id": "Id 2"},
                "player2": {"id": "Id 3"},
                "turns": [{"playerOnTurn": "Id 3", "cardDrawn": false}],
                "state": "OPEN"
            }""", result, false)
    }

    @Test
    fun `does not deliver game but status code 404 if game not found`() {

        // Given
        every { gameService.retrieveGame("1") } returns null

        val requestBuilder = MockMvcRequestBuilders
                .get("/game/1")
                .accept(MediaType.APPLICATION_JSON)

        // When
        val statusCode = mockMvc.perform(requestBuilder)
                .andReturn()
                .response
                .status

        // Then
        assertThat(statusCode).isEqualTo(404)
    }

    @Test
    fun `delivers all games`() {
        // Given
        val game = Game(
                "1",
                player1 = Player("Id 2"),
                player2 = Player("Id 3"),
                turns = listOf(Turn(playerOnTurn = Player("Id 3"))),
                state = OPEN)
        every { gameService.retrieveGames() } returns listOf(game.toExternalModel())

        val requestBuilder = MockMvcRequestBuilders
                .get("/game")
                .accept(MediaType.APPLICATION_JSON)

        // When
        val result = mockMvc.perform(requestBuilder)
                .andReturn()
                .response
                .contentAsString

        // Then
        JSONAssert.assertEquals("""[{
                "id": "1",
                "player1": {"id": "Id 2"},
                "player2": {"id": "Id 3"},
                "turns": [{"playerOnTurn": "Id 3", "cardDrawn": false}],
                "state": "OPEN"
            }]""", result, false)
    }

    @Test
    fun `delivers empty list if no games are available`() {
        // Given
        every { gameService.retrieveGames() } returns emptyList()

        val requestBuilder = MockMvcRequestBuilders
                .get("/game")
                .accept(MediaType.APPLICATION_JSON)

        // When
        val result = mockMvc.perform(requestBuilder)
                .andReturn()
                .response
                .contentAsString

        // Then
        JSONAssert.assertEquals("""[]""", result, false)
    }

    @Test
    fun `delivers new game`() {

        // Given
        val accountId1 = "Account 1"
        val accountId2 = "Account 2"
        val game = Game(
                "Id 1",
                player1 = Player("Id 2", name = accountId1),
                player2 = Player("Id 3", name = accountId2),
                state = OPEN)
        every { gameService.createGame(accountId1, accountId2) } returns game.toExternalModel()

        val requestBuilder = MockMvcRequestBuilders
                .post("/game")
                .content("""{
                    "accountId1": "Account 1",
                    "accountId2": "Account 2"
                }""")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)

        // When
        val result = mockMvc.perform(requestBuilder)
                .andReturn()
                .response
                .contentAsString

        // Then
        JSONAssert.assertEquals(
                """{
                    "id": "Id 1",
                    "player1": {"id": "Id 2", "name": "Account 1"},
                    "player2": {"id": "Id 3", "name": "Account 2"},
                    "turns": [{"playerOnTurn": "Id 2", "cardDrawn": false}],
                    "state": "OPEN"
                }""",
                result,
                false
        )
    }

    @Test
    fun `resolves event and returns updated game`() {

        // Given
        val gameId = "1"
        val game = Game(
                gameId,
                player1 = Player("Id 2"),
                player2 = Player("Id 3"),
                state = OPEN)
        every { gameService.commitGameEvent(gameId,
                                            NoOpEventDTO()) } returns game.toExternalModel()

        val requestBuilder = MockMvcRequestBuilders
                .post("/game/1/event")
                .content("""{"event": {"type": "NO_OP", "noopValue": ""}}""")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)

        // When
        val result = mockMvc.perform(requestBuilder)
                .andReturn()
                .response
                .contentAsString

        // Then
        verify { gameService.commitGameEvent(gameId,
                                             NoOpEventDTO()) }
        JSONAssert.assertEquals(
                "{}",
                result,
                false
        )
    }

    @Test
    fun `does not resolve event but returns status code 404 if game not found`() {

        // Given
        val gameId = "1"
        every { gameService.commitGameEvent(gameId, NoOpEventDTO()) } returns null
        val requestBuilder = MockMvcRequestBuilders
                .post("/game/1/event")
                .content("""{
                    "event": {"type": "NO_OP", "noopValue": ""}
                }""")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)

        // When
        val statusCode = mockMvc.perform(requestBuilder)
                .andReturn()
                .response
                .status

        // Then
        assertThat(statusCode).isEqualTo(404)
    }
}
