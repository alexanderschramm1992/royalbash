package de.schramm.royalbash.core.domain.card.summoningcard.defendingtarget;

import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.AttackableTarget;
import de.schramm.royalbash.core.domain.game.board.player.field.Summoning;
import de.schramm.royalbash.core.exception.GameEngineException;

public interface DefendingTargetEffect {

    void apply(
            Summoning attackingSummoning,
            AttackableTarget attackedTarget,
            Summoning defendingSummoning
    ) throws GameEngineException;
}
