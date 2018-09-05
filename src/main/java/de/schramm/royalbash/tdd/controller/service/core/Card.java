package de.schramm.royalbash.tdd.controller.service.core;

import de.schramm.royalbash.tdd.controller.service.core.Context;
import de.schramm.royalbash.tdd.controller.service.core.Game;

public interface Card {
    Game invoke(Context context);
    boolean canBePlacedOnSpot();
}
