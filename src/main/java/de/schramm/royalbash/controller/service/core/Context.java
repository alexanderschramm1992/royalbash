package de.schramm.royalbash.controller.service.core;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

@Value
@Builder(toBuilder = true)
public class Context {

    private final Game game;
    private final Player owner;
    private final Player targetPlayer;
    private final Spot targetSpot;
    private final Creature targetCreature;

    public Optional<Player> getTargetPlayer() {
        return Optional.ofNullable(targetPlayer);
    }

    public Optional<Spot> getTargetSpot() {
        return Optional.ofNullable(targetSpot);
    }

    public Optional<Creature> getTargetCreature() {
        return Optional.ofNullable(targetCreature);
    }
}
