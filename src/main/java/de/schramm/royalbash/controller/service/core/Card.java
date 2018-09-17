package de.schramm.royalbash.controller.service.core;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;

public interface Card {
    Game invoke(Context context);
    String getId();
    String getName();
    int getCost();
    boolean isPlaceableOnSpot();
}
