package de.schramm.royalbash.controller.service.gameevent;

import de.schramm.royalbash.controller.service.core.Game;

public interface GameEvent {
    GameEventType getType();
    Game invoke(Game game);
}
