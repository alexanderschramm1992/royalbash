package de.schramm.royalbash.gameengine.model.card;

import de.schramm.royalbash.gameengine.model.Game;
import de.schramm.royalbash.gameengine.model.Player;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CardContext {

    private Game game;
    private Player owner;
}
