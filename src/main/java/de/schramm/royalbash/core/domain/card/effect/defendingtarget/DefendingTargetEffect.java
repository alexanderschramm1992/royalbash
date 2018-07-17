package de.schramm.royalbash.core.domain.card.effect.defendingtarget;

import de.schramm.royalbash.core.exception.GameBrokenException;
import de.schramm.royalbash.core.exception.GameEngineException;
import de.schramm.royalbash.core.exception.RuleViolationException;
import de.schramm.royalbash.core.domain.game.board.player.field.Summoning;
import de.schramm.royalbash.core.domain.game.board.player.field.Target;
import de.schramm.royalbash.core.domain.card.EffectContext;

import java.util.UUID;

public interface DefendingTargetEffect {

    void apply(
            UUID attackingSummoningId,
            UUID attackedTargetId,
            UUID defendingSummoningId,
            EffectContext context
    ) throws GameEngineException;
}
