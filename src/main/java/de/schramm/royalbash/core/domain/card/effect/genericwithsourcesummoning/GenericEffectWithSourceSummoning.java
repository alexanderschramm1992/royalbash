package de.schramm.royalbash.core.domain.card.effect.genericwithsourcesummoning;

import de.schramm.royalbash.core.exception.RuleViolationException;
import de.schramm.royalbash.core.domain.game.board.player.field.Summoning;
import de.schramm.royalbash.core.domain.card.EffectContext;

public interface GenericEffectWithSourceSummoning {

    void apply(Summoning source, EffectContext context) throws RuleViolationException;
}
