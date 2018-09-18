package de.schramm.royalbash.controller.service.core.card;

import de.schramm.royalbash.controller.service.core.Card;
import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import de.schramm.royalbash.controller.service.core.Player;
import de.schramm.royalbash.controller.service.core.effect.DealDamageToPlayerEffect;
import lombok.Builder;
import lombok.Value;
import lombok.val;

@Value
@Builder
public class Fireball implements Card {

    private final String id;
    private final int cost;

    private final String name = "Fireball";
    private final boolean placeableOnSpot = false;
    private final DealDamageToPlayerEffect effect = DealDamageToPlayerEffect.builder()
            .amountOfDamage(2)
            .build();

    @Override
    public Game invoke(Context context) {
        return effect.invoke(context);
    }
}
