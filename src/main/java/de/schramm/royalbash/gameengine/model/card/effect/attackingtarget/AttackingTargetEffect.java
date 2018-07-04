package de.schramm.royalbash.gameengine.model.card.effect.attackingtarget;

import de.schramm.royalbash.gameengine.exception.GameBrokenException;
import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import de.schramm.royalbash.gameengine.model.Summoning;
import de.schramm.royalbash.gameengine.model.Target;
import de.schramm.royalbash.gameengine.model.card.EffectContext;

public interface AttackingTargetEffect {

    void apply(
            Summoning attackingSummoning,
            Target attackedTarget,
            EffectContext context
    ) throws RuleViolationException, GameBrokenException;
}
