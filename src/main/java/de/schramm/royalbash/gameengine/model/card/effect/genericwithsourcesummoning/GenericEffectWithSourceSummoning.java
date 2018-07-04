package de.schramm.royalbash.gameengine.model.card.effect.genericwithsourcesummoning;

import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import de.schramm.royalbash.gameengine.model.Summoning;
import de.schramm.royalbash.gameengine.model.card.EffectContext;

public interface GenericEffectWithSourceSummoning {

    void apply(Summoning source, EffectContext context) throws RuleViolationException;
}
