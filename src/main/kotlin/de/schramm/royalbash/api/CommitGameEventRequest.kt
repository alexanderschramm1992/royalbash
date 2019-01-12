package de.schramm.royalbash.api

import de.schramm.royalbash.application.gameevent.GameEvent
import de.schramm.royalbash.application.gameevent.NoOpEvent

internal class CommitGameEventRequest(val event: GameEvent) {
    constructor(): this(event = NoOpEvent())
}
