package de.schramm.royalbash.controller.service.core.card;

import de.schramm.royalbash.controller.service.core.Card;
import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.effect.DiscardHandcardsEffect;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
class MemoryLeak implements Card {

    private final String id;
    private final int cost;

    private final String name = "Memory Leak";
    private final boolean placeableOnSpot = false;
    private final DiscardHandcardsEffect effect = DiscardHandcardsEffect.builder()
            .amountOfCards(2)
            .build();

    @Override
    public Game invoke(Context context) {
        return effect.invoke(context);
    }
}
