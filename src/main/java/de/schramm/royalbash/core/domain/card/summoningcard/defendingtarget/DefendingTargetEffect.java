package de.schramm.royalbash.core.domain.card.summoningcard.defendingtarget;

import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.AttackableTarget;
import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.FightableSummoning;
import de.schramm.royalbash.core.exception.RuleViolationException;

public interface DefendingTargetEffect {

    void apply(
            FightableSummoning attackingSummoning,
            AttackableTarget attackedTarget,
            FightableSummoning defendingSummoning
    ) throws RuleViolationException;
}
