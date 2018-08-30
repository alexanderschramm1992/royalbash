package de.schramm.royalbash.tdd.core;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Context {

    private final Game game;
    private final Player targetPlayer;
}
