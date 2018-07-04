package de.schramm.royalbash.gameengine.model.card.effect.defendingtarget;

import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import de.schramm.royalbash.gameengine.model.Summoning;
import de.schramm.royalbash.gameengine.model.Target;
import de.schramm.royalbash.gameengine.model.card.EffectContext;

public class PlainDefendingTargetEffect implements DefendingTargetEffect {

    @Override
    public void apply(
            Summoning attackingSummoning,
            Target attackedTarget,
            Summoning defendingSummoning,
            EffectContext context
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
