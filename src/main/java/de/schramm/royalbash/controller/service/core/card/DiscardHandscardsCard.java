package de.schramm.royalbash.controller.service.core.card;

import de.schramm.royalbash.controller.service.core.Card;
import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.effect.DiscardHandcardsEffect;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
class DiscardHandscardsCard implements Card {

    private final String id;
    private final String name;
    private final DiscardHandcardsEffect effect;
    private final int cost;

    @Override
    public Game invoke(Context context) {
        return effect.invoke(context);
    }

    @Override
    public boolean isPlaceableOnSpot() {
        return false;
    }
}
