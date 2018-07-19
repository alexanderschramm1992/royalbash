package de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget;

import de.schramm.royalbash.core.domain.game.board.player.field.Summoning;
import de.schramm.royalbash.core.exception.GameEngineException;

public interface AttackingTargetEffect {

    void apply(
            Summoning attackingSummoning,
            AttackableTarget attackedTarget
    ) throws GameEngineException;
}
