package de.schramm.royalbash.core.domain.card.summoningcard.effectwithsourcesummoning;

import de.schramm.royalbash.core.exception.RuleViolationException;
import de.schramm.royalbash.core.domain.game.board.player.field.Summoning;

public interface GenericEffectWithSourceSummoning {

    void apply(Summoning source, EffectContext context) throws RuleViolationException;
}
