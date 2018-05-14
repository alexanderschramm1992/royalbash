package de.schramm.royalbash.controller.requestmodel;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class GameRequest {

    private UUID gameId;
}
