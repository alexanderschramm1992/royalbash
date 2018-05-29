package de.schramm.royalbash.controller.requestmodel;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class PlayResourcesCardRequest {

    private UUID gameId;
    private UUID playerId;
    private UUID cardId;
}
