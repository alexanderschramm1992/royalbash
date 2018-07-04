package de.schramm.royalbash.gameengine.model.card.effect.defendingtarget;

import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import de.schramm.royalbash.gameengine.model.Summoning;
import de.schramm.royalbash.gameengine.model.Target;
import de.schramm.royalbash.gameengine.model.card.EffectContext;

public interface DefendingTargetEffect {

    void apply(
            Summoning attackingSummoning,
            Target attackedTarget,
            Summoning defendingSummoning,
            EffectContext context
    ) throws RuleViolationException;
}
