package de.schramm.royalbash.application

import de.schramm.royalbash.application.gameevent.CardPlayedOnSpotEventDTO
import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.Player
import de.schramm.royalbash.domain.Spot
import de.schramm.royalbash.domain.card.creature.CreatureMock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UsecaseTests {

    @Autowired
    private lateinit var games: Games

    @Autowired
    private lateinit var gameService: GameService

    @Test
    fun `As Player I want to play creature cards in order to power up my forces`() {

        // Given
        val creature = CreatureMock(id = "creature",
                                    instanceId = "creatureInstance",
                                    cost = 2)
        val spot = Spot(id = "spot")
        val player1 = Player(id = "player1",
                             handcards = listOf(creature),
                             spots = listOf(spot),
                             resources = 2)
        val player2 = Player(id = "player2")
        val game = Game(id = "game",
                        player1 = player1,
                        player2 = player2)
        games.save(game)

        val event = CardPlayedOnSpotEventDTO(cardInstanceId = creature.instanceId,
                                             ownerId = player1.id,
                                             targetSpotId = player1.spots.first().id)
        // When
        gameService.commitGameEvent(game.id, event)
        // Then
        val updatedGame = games.findById(game.id)
        assertThat(updatedGame?.player1?.handcards).isEmpty()
        assertThat(updatedGame?.player1?.resources).isEqualTo(2)
        assertThat(updatedGame?.player1?.spots?.first()?.creature?.instanceId).isEqualTo(creature.instanceId)
    }
}
