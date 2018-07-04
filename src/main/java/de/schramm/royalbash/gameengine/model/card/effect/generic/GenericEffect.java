package de.schramm.royalbash.gameengine.model.card.effect.generic;

import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import de.schramm.royalbash.gameengine.model.card.EffectContext;

public interface GenericEffect {

    void apply(EffectContext context) throws RuleViolationException;
}
