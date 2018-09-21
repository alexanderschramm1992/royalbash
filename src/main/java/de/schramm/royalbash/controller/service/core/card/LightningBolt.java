package de.schramm.royalbash.controller.service.core.card;

import de.schramm.royalbash.controller.service.core.Card;
import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.effect.DealDamageToCreatureEffect;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LightningBolt implements Card {

    private final String id;
    private final int cost;

    private final String name = "Lightning Bolt";
    private final boolean placeableOnSpot = false;
    private final DealDamageToCreatureEffect effect = DealDamageToCreatureEffect.builder()
            .amountOfDamage(2)
            .build();

    @Override
    public Game invoke(final Context context) {
        return effect.invoke(context);
    }
}
