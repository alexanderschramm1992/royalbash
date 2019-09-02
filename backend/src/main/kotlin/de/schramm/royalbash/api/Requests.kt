package de.schramm.royalbash.api

import de.schramm.royalbash.infrastructure.gameevent.GameEventDTO
import de.schramm.royalbash.infrastructure.gameevent.NoOpEventDTO

data class CommitGameEventRequest(val event: GameEventDTO = NoOpEventDTO())

data class CreateGameRequest(val accountId1: String = "",
                             val accountId2: String = "")
