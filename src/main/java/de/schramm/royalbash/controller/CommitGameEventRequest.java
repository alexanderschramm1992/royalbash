package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.service.gameevent.GameEvent;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
class CommitGameEventRequest {

    private final String gameId;
    private final GameEvent event;
}
