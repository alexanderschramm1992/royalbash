package de.schramm.royalbash.controller

import de.schramm.royalbash.controller.service.gameevent.GameEvent
import de.schramm.royalbash.controller.service.gameevent.NoOpEvent

internal class CommitGameEventRequest(val event: GameEvent) {
    constructor(): this(event = NoOpEvent())
}
