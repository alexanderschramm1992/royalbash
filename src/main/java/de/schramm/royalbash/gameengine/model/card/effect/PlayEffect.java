package de.schramm.royalbash.gameengine.model.card.effect;

import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import de.schramm.royalbash.gameengine.model.card.EffectContext;

public interface PlayEffect {

    void apply(EffectContext context) throws RuleViolationException;
}
