package de.schramm.royalbash.gameengine.model.card.effect;

import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import de.schramm.royalbash.gameengine.model.Summoning;
import de.schramm.royalbash.gameengine.model.card.EffectContext;

public interface AttackSummoningEffect {

    void apply(
            Summoning attackingSummoning,
            Summoning attackedSummoning,
            EffectContext context
    ) throws RuleViolationException;
}
