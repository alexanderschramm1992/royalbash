package de.schramm.royalbash.api

import de.schramm.royalbash.application.GameService
import de.schramm.royalbash.infrastructure.gameevent.*
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.State.OPEN
import de.schramm.royalbash.infrastructure.database.InMemoryRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import java.util.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [(GameController::class)], secure = false)
class EventTranslationTest {

    @TestConfiguration
    open class ControllerTestConfig {
        @Bean
        open fun gameService() = mockk<GameService>()
        @Bean
        open fun gameRepository() = InMemoryRepository()
    }

    private val gameId = "1"

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var gameService: GameService

    @BeforeEach
    fun define_mock_behavior() {

        val gameOptional = Optional.of(Game(
                gameId,
                player1 = Player("Id 2"),
                player2 = Player("Id 3"),
                playerOnTurn = Player("Id 2"),
                state = OPEN))

        every { gameService.commitGameEvent(any(), any()) } returns gameOptional
    }

    @Test
    @Throws(Exception::class)
    fun should_translate_CardDrawnEvent() {

        // Given
        val json = """{
            "event": {
                "type": "CARD_DRAWN",
                "amountOfCards": 1,
                "playerId": "Player Id"
            }
        }"""
        val expectedEvent = CardDrawnEventDTO("Player Id", 1)

        // When Then
        test(json, expectedEvent)
    }

    @Test
    @Throws(Exception::class)
    fun should_translate_CardPlayedOnPlayerEvent() {

        // Given
        val json = "{\"event\": {" +
                        "\"type\": \"CARD_PLAYED_ON_PLAYER\", " +
                        "\"cardId\": \"Card Id\", " +
                        "\"ownerId\": \"Owner Id\", " +
                        "\"targetPlayerId\": \"Target Player Id\"}" +
                        "}"
        val expectedEvent = CardPlayedOnPlayerEventDTO(
                "Card Id",
                "Owner Id",
                "Target Player Id")

        // When Then
        test(json, expectedEvent)
    }

    @Test
    @Throws(Exception::class)
    fun should_translate_CreatureAttackedEvent() {

        // Given
        val json = "{\"event\": {" +
                        "\"type\": \"CREATURE_ATTACKED\", " +
                        "\"attackerId\": \"Attacker Id\", " +
                        "\"defenderId\": \"Defender Id\", " +
                        "\"ownerId\": \"Owner Id\"}" +
                        "}"
        val expectedEvent = CreatureAttackedEventDTO(
                attackerId = "Attacker Id",
                defenderId = "Defender Id",
                ownerId = "Owner Id")

        // When Then
        test(json, expectedEvent)
    }

    @Test
    @Throws(Exception::class)
    fun should_translate_NoOpEvent() {

        // Given
        val json = String.format(
                "{\"event\": {\"type\": \"NO_OP\"}}",
                gameId
        )
        val expectedEvent = NoOpEventDTO()

        // When Then
        test(json, expectedEvent)
    }

    @Test
    @Throws(Exception::class)
    fun should_translate_PlayerAttackedEvent() {

        // Given
        val json = "{\"event\": {" +
                        "\"type\": \"PLAYER_ATTACKED\", " +
                        "\"creatureId\": \"Creature Id\", " +
                        "\"ownerId\": \"Owner Id\"}" +
                        "}"
        val expectedEvent = PlayerAttackedEventDTO("Creature Id", "Owner Id")

        // When Then
        test(json, expectedEvent)
    }

    @Test
    @Throws(Exception::class)
    fun should_translate_TurnEndedEvent() {

        // Given
        val json = String.format(
                "{\"event\": {" +
                        "\"type\": \"TURN_ENDED\", " +
                        "\"playerId\": \"Player Id\"}" +
                        "}",
                gameId
        )
        val expectedEvent = TurnEndedEventDTO("Player Id")

        // When Then
        test(json, expectedEvent)
    }

    @Throws(Exception::class)
    private fun test(json: String, expectedEvent: GameEventDTO) {

        // Given
        val requestBuilder = MockMvcRequestBuilders
                .post("/game/1/event")
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)

        // When
        mockMvc.perform(requestBuilder)
                .andReturn()
                .response
                .contentAsString

        // Then
        verify { gameService.commitGameEvent(gameId, expectedEvent) }
    }
}
