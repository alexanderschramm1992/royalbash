package de.schramm.royalbash.controller.service.gameevent;

import de.schramm.royalbash.controller.service.core.Game;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NoOpEvent implements GameEvent {

    @Override
    public Game invoke(Game game) {
        return game;
    }
}
