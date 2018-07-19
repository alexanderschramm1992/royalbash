package de.schramm.royalbash.core.domain.card.summoningcard.defendingtarget;

import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.AttackableTarget;
import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.FightableSummoning;
import de.schramm.royalbash.core.exception.RuleViolationException;

public class PlainDefendingTargetEffect implements DefendingTargetEffect {

    @Override
    public void apply(
            FightableSummoning attackingSummoning,
            AttackableTarget attackedTarget,
            FightableSummoning defendingSummoning
    ) throws RuleViolationException {

        if(!attackedTarget.isOccupied() || !attackedTarget.getSummoning().equals(defendingSummoning)) {
            throw new RuleViolationException(String.format(
                    "Defending summoning %s does not occupy attacked target %s",
                    defendingSummoning.getId(),
                    attackedTarget.getId()
            ));
        }

        defendingSummoning.dealDamage(attackingSummoning);
    }
}
