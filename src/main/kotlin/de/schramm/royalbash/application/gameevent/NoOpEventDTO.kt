package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.UUIDGenerator

data class NoOpEventDTO(val noopValue: String) : GameEventDTO {

    constructor(): this(noopValue = "")

    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game = game
}
