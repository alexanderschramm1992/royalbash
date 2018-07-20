package de.schramm.royalbash.core.domain.card.effect;

import de.schramm.royalbash.core.exception.RuleViolationException;

public interface ResourcePoolAccessor {
    void alterRations(int rations) throws RuleViolationException;
    void alterMaterial(int material) throws RuleViolationException;
    void alterBlessing(int blessing) throws RuleViolationException;
}
