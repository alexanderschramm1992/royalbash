package de.schramm.royalbash.controller.service.core.card;

import de.schramm.royalbash.controller.service.core.Card;
import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.effect.DrawHandcardsEffect;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ByondInsight implements Card {

    private final String id;
    private final int cost;

    private final String name = "Beyond Insight";
    private final boolean placeableOnSpot = false;
    private final DrawHandcardsEffect effect = DrawHandcardsEffect.builder()
            .amountOfCards(2)
            .build();

    @Override
    public Game invoke(Context context) {
        return effect.invoke(context);
    }
}
