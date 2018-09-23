package de.schramm.royalbash.controller.service.core.effect;

import de.schramm.royalbash.controller.service.core.Context;
import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;
import lombok.val;

import java.util.Optional;

@Value
@Builder
public class RemoveCreatureEffect {

    public Game invoke(Context context) {

        val game = context.getGame();

        return context.getTargetCreature()
                .map(game::findCreature)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(game::removeCreature)
                .orElse(game);
    }
}
