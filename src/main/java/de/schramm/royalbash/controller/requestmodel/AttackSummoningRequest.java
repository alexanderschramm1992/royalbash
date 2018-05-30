package de.schramm.royalbash.controller.requestmodel;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class AttackSummoningRequest {

    private UUID gameId;
    private UUID playerId;
    private UUID attackingSummoningId;
    private UUID attackedSummoningId;
}
