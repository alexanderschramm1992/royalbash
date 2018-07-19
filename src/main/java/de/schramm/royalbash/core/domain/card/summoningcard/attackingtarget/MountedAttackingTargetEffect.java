package de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget;

import de.schramm.royalbash.core.domain.card.summoningcard.Tag;
import de.schramm.royalbash.core.domain.game.board.player.field.Summoning;
import de.schramm.royalbash.core.domain.game.board.player.field.Target;
import de.schramm.royalbash.core.exception.GameEngineException;
import lombok.val;

public class MountedAttackingTargetEffect implements AttackingTargetEffect {

    @Override
    public void apply(
            Summoning attackingSummoning,
            Target attackedTarget
    ) throws GameEngineException {

        if(attackedTarget.isOccupied()) {
            val attackedSummoning = attackedTarget.getSummoning();
            if(attackingSummoning.hasTag(Tag.MOUNTED)) {
                new PlainAttackingTargetEffect().apply(
                        attackingSummoning,
                        attackedTarget
                );
            } else {
                attackingSummoning.dealDamage(attackedSummoning);
                if(attackedSummoning.getCurrentHealth() > 0) {
                    attackedSummoning.defendTarget(
                            attackingSummoning,
                            attackedTarget
                    );
                }
            }
        }
    }
}
