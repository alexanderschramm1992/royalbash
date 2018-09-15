package de.schramm.royalbash.controller.service.core;

public interface Card {
    Game invoke(Context context);
    String getId();
    boolean isPlaceableOnSpot();
}