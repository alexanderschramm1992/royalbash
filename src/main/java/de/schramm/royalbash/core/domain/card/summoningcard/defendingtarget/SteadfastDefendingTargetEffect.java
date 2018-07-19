package de.schramm.royalbash.core.domain.card.summoningcard.defendingtarget;

import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.AttackableTarget;
import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.FightableSummoning;
import de.schramm.royalbash.core.exception.RuleViolationException;

public class SteadfastDefendingTargetEffect implements DefendingTargetEffect {
    @Override
    public void apply(
            FightableSummoning attackingSummoning,
            AttackableTarget attackedTarget,
            FightableSummoning defendingSummoning
    ) throws RuleViolationException {

        defendingSummoning.increaseCurrentStrength(1);
        new PlainDefendingTargetEffect().apply(
                attackingSummoning,
                attackedTarget,
                defendingSummoning
        );
        defendingSummoning.reduceCurrentStrength(1);
    }
}
