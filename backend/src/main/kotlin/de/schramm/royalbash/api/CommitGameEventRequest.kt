package de.schramm.royalbash.api

import de.schramm.royalbash.infrastructure.gameevent.GameEventDTO
import de.schramm.royalbash.infrastructure.gameevent.NoOpEventDTO

internal class CommitGameEventRequest(val event: GameEventDTO) {
    constructor(): this(event = NoOpEventDTO())
}
