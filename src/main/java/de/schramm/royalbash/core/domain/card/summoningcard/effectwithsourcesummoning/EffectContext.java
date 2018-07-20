package de.schramm.royalbash.core.domain.card.summoningcard.effectwithsourcesummoning;

import de.schramm.royalbash.core.domain.game.Game;
import de.schramm.royalbash.core.domain.game.board.player.Player;
import de.schramm.royalbash.core.exception.GameBrokenException;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EffectContext {

    private Game game;
    private Player owner;

    public Player getEnemy() throws GameBrokenException {

        return game.findOpponent(owner);
    }
}
