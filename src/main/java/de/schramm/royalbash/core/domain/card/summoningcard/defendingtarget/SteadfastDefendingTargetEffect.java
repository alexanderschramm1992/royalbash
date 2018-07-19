package de.schramm.royalbash.core.domain.card.summoningcard.defendingtarget;

import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.AttackableTarget;
import de.schramm.royalbash.core.domain.game.board.player.field.Summoning;
import de.schramm.royalbash.core.exception.GameEngineException;

public class SteadfastDefendingTargetEffect implements DefendingTargetEffect {
    @Override
    public void apply(
            Summoning attackingSummoning,
            AttackableTarget attackedTarget,
            Summoning defendingSummoning
    ) throws GameEngineException {

        defendingSummoning.increaseCurrentStrength(1);
        new PlainDefendingTargetEffect().apply(
                attackingSummoning,
                attackedTarget,
                defendingSummoning
        );
        defendingSummoning.reduceCurrentStrength(1);
    }
}
