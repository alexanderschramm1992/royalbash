package de.schramm.royalbash.controller.service.gameevent;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AttackEvent implements GameEvent {
    private final GameEventType type = GameEventType.ATTACK_PLAYER;
}
