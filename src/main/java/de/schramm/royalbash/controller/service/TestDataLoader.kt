package de.schramm.royalbash.controller.service

import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import de.schramm.royalbash.controller.service.core.State
import de.schramm.royalbash.controller.service.core.card.Fireball
import de.schramm.royalbash.controller.service.core.card.MemoryLeak
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
open class TestDataLoader @Autowired constructor(private val repository: GameRepository) {

    @PostConstruct
    fun loadTestData() {

        val fireball = Fireball(
                id = "fireball",
                cost = 2)
        val memoryLeak = MemoryLeak(
                id = "memoryleak",
                cost = 2
        )
        val player1 = Player(
                id = "player1",
                name = "Player 1",
                hitpoints = 25,
                handcards = listOf(fireball, memoryLeak))
        val player2 = Player(
                id = "player2",
                name = "Player 2")
        val game = Game(
                id = "1",
                player1 = player1,
                player2 = player2,
                playerOnTurn = player1,
                state = State.OPEN)

        repository.save(game)
    }
}
