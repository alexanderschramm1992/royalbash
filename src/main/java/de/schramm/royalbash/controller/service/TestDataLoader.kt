package de.schramm.royalbash.controller.service

import de.schramm.royalbash.controller.service.core.Game
import de.schramm.royalbash.controller.service.core.Player
import de.schramm.royalbash.controller.service.core.State
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
open class TestDataLoader @Autowired constructor(private val repository: GameRepository) {

    @PostConstruct
    fun loadTestData() {

        val game = Game(
                id = "1",
                player1 = Player(
                        id = "2",
                        name = "Player 1"),
                player2 = Player(
                        id = "3",
                        name = "Player 2"),
                playerOnTurn = Player(
                        id = "2",
                        name = "Player 1"),
                state = State.OPEN)

        repository.save(game)
    }
}
