package de.schramm.royalbash.model.card.instance;

import de.schramm.royalbash.model.InstanceType;

import java.util.UUID;

public interface CardInstance {

    UUID getId();
    InstanceType getInstanceType();
    String getName();

    int getCost();
}
