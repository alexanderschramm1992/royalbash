package de.schramm.royalbash.controller.service.core.effect;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;
import lombok.val;

@Value
@Builder
public class DealDamageToCreatureEffect {

    private final int amountOfDamage;

    Game invoke(final Context context) {

        val game = context.getGame();

        return context.getTargetCreature()
                .map(creature -> game.updateCreature(creature, creature.damage(amountOfDamage)))
                .orElse(game);
    }
}
