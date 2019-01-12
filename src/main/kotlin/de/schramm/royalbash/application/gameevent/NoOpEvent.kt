package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.Game

data class NoOpEvent(val noopValue: String) : GameEvent {

    constructor(): this(noopValue = "")

    override fun invoke(game: Game): Game {
        return game
    }
}
