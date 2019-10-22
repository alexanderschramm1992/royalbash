package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.application.UUIDGenerator
import de.schramm.royalbash.domain.Game

interface GameEventDTO {
    fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game
}
