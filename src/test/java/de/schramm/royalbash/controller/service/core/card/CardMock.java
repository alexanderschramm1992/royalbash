package de.schramm.royalbash.controller.service.core.card;

import de.schramm.royalbash.controller.service.core.Card;
import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CardMock implements Card {

    private final String id;
    private final String name;
    private final int cost;

    private final boolean placeableOnSpot = false;

    @Override
    public Game invoke(final Context context) {
        return context.getGame();
    }
}
