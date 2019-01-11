package de.schramm.royalbash.controller.service.gameevent

import de.schramm.royalbash.controller.service.core.Game

data class NoOpEvent(val noopValue: String) : GameEvent {

    constructor(): this(noopValue = "")

    override fun invoke(game: Game): Game {
        return game
    }
}
