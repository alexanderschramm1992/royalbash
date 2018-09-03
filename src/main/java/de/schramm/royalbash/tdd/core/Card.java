package de.schramm.royalbash.tdd.core;

import de.schramm.royalbash.tdd.core.Context;
import de.schramm.royalbash.tdd.core.Game;

public interface Card {
    Game invoke(Context context);
    boolean canBePlacedOnSpot();
}
