package de.schramm.royalbash.model.card;

import de.schramm.royalbash.model.InstanceType;
import de.schramm.royalbash.model.card.instance.CardInstance;

import java.util.UUID;

public interface Card {

    UUID getId();

    InstanceType getInstanceType();

    String getName();

    int getCost();

    CardInstance toInstance();
}
