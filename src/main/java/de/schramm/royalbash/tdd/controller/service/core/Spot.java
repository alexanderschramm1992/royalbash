package de.schramm.royalbash.tdd.controller.service.core;

import lombok.Builder;
import lombok.Value;
import lombok.val;

import java.util.Optional;

@Value
@Builder(toBuilder = true)
public class Spot {

    private final Creature creature;

    public Optional<Creature> getCreature() {
        return Optional.ofNullable(creature);
    }

    public Spot place(Creature creature) {

        if(!creature.canBePlacedOnSpot()) {
            return this;
        }

        return this.toBuilder()
                .creature(creature)
                .build();
    }

    Spot updateCreature(Creature oldCreature, Creature newCreature) {

        val updatedCreature = getCreature()
                .map(creature -> creature.equals(oldCreature) ? newCreature : creature)
                .orElse(null);

        return this.toBuilder()
                .creature(updatedCreature)
                .build();
    }

    Spot removeCreature(Creature creature) {
        return this.toBuilder()
                .creature(creature.equals(this.creature) ? null : this.creature)
                .build();
    }
}
