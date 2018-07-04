package de.schramm.royalbash.gameengine.model.card.effect.defendingtarget;

import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import de.schramm.royalbash.gameengine.model.Summoning;
import de.schramm.royalbash.gameengine.model.Target;
import de.schramm.royalbash.gameengine.model.card.EffectContext;

public class SteadfastDefendingTargetEffect implements DefendingTargetEffect {
    @Override
    public void apply(
            Summoning attackingSummoning,
            Target attackedTarget,
            Summoning defendingSummoning,
            EffectContext context
    ) throws RuleViolationException {

        defendingSummoning.increaseCurrentStrength(1);
        new PlainDefendingTargetEffect().apply(
                attackingSummoning,
                attackedTarget,
                defendingSummoning,
                context
        );
        defendingSummoning.reduceCurrentStrength(1);
    }
}
