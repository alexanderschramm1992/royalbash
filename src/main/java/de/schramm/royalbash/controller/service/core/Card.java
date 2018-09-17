package de.schramm.royalbash.controller.service.core;

public interface Card {
    Game invoke(CardOnPlayerContext cardOnPlayerContext);
    String getId();
    String getName();
    int getCost();
    boolean isPlaceableOnSpot();
}
