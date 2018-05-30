package de.schramm.royalbash.gameengine.model.card.effect;

import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import de.schramm.royalbash.gameengine.model.card.CardContext;

public interface PlayEffect {

    void apply(CardContext context) throws RuleViolationException;
}
