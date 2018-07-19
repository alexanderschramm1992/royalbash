package de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget;

import de.schramm.royalbash.core.exception.RuleViolationException;
import lombok.val;

public class PlainAttackingTargetEffect implements AttackingTargetEffect {

    @Override
    public void apply(
            FightableSummoning attackingSummoning,
            AttackableTarget attackedTarget
    ) throws RuleViolationException {

        if(attackedTarget.isOccupied()) {

            val attackedSummoning = attackedTarget.getSummoning();
            attackingSummoning.dealDamage(attackedSummoning);
            attackedSummoning.defendTarget(
                    attackingSummoning,
                    attackedTarget
            );

            attackingSummoning.setCanAttack(false);
        }
    }
}
