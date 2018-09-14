package de.schramm.royalbash.controller.service.gameevent;

import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AttackEvent implements GameEvent {

    private final GameEventType type = GameEventType.ATTACK_PLAYER;

    private final String creatureId;
    private final String ownerId;

    @Override
    public Game invoke(Game game) {
        return game
                .findPlayer(ownerId)
                .map(player-> game.
                        findCreature(creatureId)
                        .map(creature -> game
                                .combat(creature, player))
                        .orElse(game)
                ).orElse(game);
    }
}
