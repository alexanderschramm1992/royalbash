package de.schramm.royalbash.controller.service.core.card;

import de.schramm.royalbash.controller.service.core.Card;
import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NoOpCard implements Card {

    private final String name;

    @Override
    public Game invoke(Context context) {
        return context.getGame();
    }

    @Override
    public boolean canBePlacedOnSpot() {
        return false;
    }
}
