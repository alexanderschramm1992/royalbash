package de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget;

import de.schramm.royalbash.core.exception.RuleViolationException;

public interface AttackingTargetEffect {

    void apply(
            FightableSummoning attackingSummoning,
            AttackableTarget attackedTarget
    ) throws RuleViolationException;
}
