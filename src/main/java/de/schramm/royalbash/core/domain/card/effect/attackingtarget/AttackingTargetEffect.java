package de.schramm.royalbash.core.domain.card.effect.attackingtarget;

import de.schramm.royalbash.core.exception.GameEngineException;
import de.schramm.royalbash.core.domain.card.EffectContext;

import java.util.UUID;

public interface AttackingTargetEffect {

    void apply(
            UUID attackingSummoningId,
            UUID attackedTargetId,
            EffectContext context
    ) throws GameEngineException;
}
