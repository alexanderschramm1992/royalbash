package de.schramm.royalbash.application.gameevent

import de.schramm.royalbash.domain.Game
import de.schramm.royalbash.domain.UUIDGenerator

interface GameEventDTO {
    operator fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game
}
