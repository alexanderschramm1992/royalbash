package de.schramm.royalbash.controller.service.gameevent;

import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreatureAttackedEvent implements GameEvent {

    private final String attackerId;
    private final String ownerId;
    private final String defenderId;

    @Override
    public Game invoke(Game game) {
        return game
                .findPlayer(ownerId)
                .map(owner -> game.
                        findCreature(attackerId)
                        .map(attacker -> game
                                .findCreature(defenderId)
                                .map(defender -> game.combat(attacker, owner, defender))
                                .orElse(game))
                        .orElse(game))
                .orElse(game);
    }
}
