package de.schramm.royalbash.controller

import de.schramm.royalbash.controller.service.GameService
import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import de.schramm.royalbash.controller.service.core.State.OPEN
import de.schramm.royalbash.controller.service.gameevent.NoOpEvent
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import java.util.*

@RunWith(SpringRunner::class)
@WebMvcTest(GameController::class)
class GameControllerTest {

    @TestConfiguration
    open class ControllerTestConfig {
        @Bean
        open fun gameService() = mockk<GameService>()
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var gameService: GameService

    @Test
    @Throws(Exception::class)
    fun should_deliver_game() {

        // Given
        val game = Game(
                "1",
                player1 = Player("Id 2"),
                player2 = Player("Id 3"),
                playerOnTurn = Player("Id 3"),
                state = OPEN)
        every { gameService.retrieveGame("1") } returns Optional.of(game)

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
                "playerOnTurn": "Id 3",
                "state": "OPEN"
            }""", result, false)
    }

    @Test
    @Throws(Exception::class)
    fun should_not_deliver_game_but_status_code_404_if_game_not_found() {

        // Given
        every { gameService.retrieveGame("1") } returns Optional.empty()

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
    @Throws(Exception::class)
    fun should_deliver_new_game() {

        // Given
        val accountId1 = "Account 1"
        val accountId2 = "Account 2"
        val game = Game(
                "Id 1",
                player1 = Player("Id 2", name = accountId1),
                player2 = Player("Id 3", name = accountId2),
                playerOnTurn = Player("Id 2"),
                state = OPEN)
        every { gameService.createGame(accountId1, accountId2) } returns game

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
                    "playerOnTurn": "Id 2",
                    "state": "OPEN"
                }""",
                result,
                false
        )
    }

    @Test
    @Throws(Exception::class)
    fun should_resolve_event_and_return_updated_game() {

        // Given
        val gameId = "1"
        val game = Game(
                gameId,
                player1 = Player("Id 2"),
                player2 = Player("Id 3"),
                playerOnTurn = Player("Id 2"),
                state = OPEN
        )
        every { gameService.commitGameEvent(gameId, NoOpEvent()) } returns Optional.of(game)

        val requestBuilder = MockMvcRequestBuilders
                .post("/game/1/event")
                .content("""{"event": {"type": "NO_OP"}}""")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)

        // When
        val result = mockMvc.perform(requestBuilder)
                .andReturn()
                .response
                .contentAsString

        // Then
        verify { gameService.commitGameEvent(gameId, NoOpEvent()) }
        JSONAssert.assertEquals(
                "{}",
                result,
                false
        )
    }

    @Test
    @Throws(Exception::class)
    fun should_not_resolve_event_but_return_status_code_404_if_game_not_found() {

        // Given
        val gameId = "1"
        every { gameService.commitGameEvent(gameId, NoOpEvent()) } returns Optional.empty()
        val requestBuilder = MockMvcRequestBuilders
                .post("/game/1/event")
                .content("""{
                    "event": {"type": "NO_OP"}
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