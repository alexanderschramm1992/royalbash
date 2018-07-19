package de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget;

import de.schramm.royalbash.core.exception.RuleViolationException;
import lombok.val;

public class VigorousAttackingTargetEffect implements AttackingTargetEffect {

    @Override
    public void apply(
            FightableSummoning attackingSummoning,
            AttackableTarget attackedTarget
    ) throws RuleViolationException {

        if(attackedTarget.isOccupied()) {
            val attackedSummoning = attackedTarget.getSummoning();
            attackingSummoning.increaseCurrentStrength(1);
            attackingSummoning.dealDamage(attackedSummoning);
            attackingSummoning.reduceCurrentStrength(1);

            attackedSummoning.defendTarget(
                    attackingSummoning,
                    attackedTarget
            );
        }
    }
}
