package de.schramm.royalbash.controller.service.core;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CardOnPlayerContext {

    private final Game game;
    private final Player owner;
    private final Player targetPlayer;
    private final Spot targetSpot;
}
