package de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget;

import de.schramm.royalbash.core.exception.RuleViolationException;
import lombok.val;

public class MountedAttackingTargetEffect implements AttackingTargetEffect {

    @Override
    public void apply(
            FightableSummoning attackingSummoning,
            AttackableTarget attackedTarget
    ) throws RuleViolationException {

        if(attackedTarget.isOccupied()) {
            val attackedSummoning = attackedTarget.getSummoning();
            if(attackingSummoning.hasTag(Tag.MOUNTED)) {
                new PlainAttackingTargetEffect().apply(
                        attackingSummoning,
                        attackedTarget
                );
            } else {
                attackingSummoning.dealDamage(attackedSummoning);
                if(attackedSummoning.getCurrentHealth() > 0) {
                    attackedSummoning.defendTarget(
                            attackingSummoning,
                            attackedTarget
                    );
                }
            }
        }
    }
}
