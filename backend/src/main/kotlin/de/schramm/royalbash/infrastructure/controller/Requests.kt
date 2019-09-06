package de.schramm.royalbash.infrastructure.controller

import de.schramm.royalbash.infrastructure.controller.gameevent.GameEventDTO
import de.schramm.royalbash.infrastructure.controller.gameevent.NoOpEventDTO

data class CommitGameEventRequest(val event: GameEventDTO = NoOpEventDTO())

data class CreateGameRequest(val accountId1: String = "",
                             val accountId2: String = "")
