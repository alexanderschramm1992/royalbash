package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.application.UUIDGenerator
import de.schramm.royalbash.domain.Game

data class NoOpEventDTO(val noopValue: String) : GameEventDTO {

    constructor(): this(noopValue = "")

    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game = game
}
