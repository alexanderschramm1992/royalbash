package de.schramm.royalbash.infrastructure.controller.gameevent

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.infrastructure.controller.gameevent.GameEventDTO

data class NoOpEventDTO(val noopValue: String) : GameEventDTO {

    constructor(): this(noopValue = "")

    override fun invoke(game: Game) = game
}
