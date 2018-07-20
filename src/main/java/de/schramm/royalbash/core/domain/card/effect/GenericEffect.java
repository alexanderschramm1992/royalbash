package de.schramm.royalbash.core.domain.card.effect;

import de.schramm.royalbash.core.exception.RuleViolationException;

public interface GenericEffect {

    void apply(GameAccessor game, PlayerAccessor owner) throws RuleViolationException;
}
