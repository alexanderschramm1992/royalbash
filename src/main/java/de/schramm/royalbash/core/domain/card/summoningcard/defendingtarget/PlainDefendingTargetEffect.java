package de.schramm.royalbash.core.domain.card.summoningcard.defendingtarget;

import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.AttackableTarget;
import de.schramm.royalbash.core.domain.game.board.player.field.Summoning;
import de.schramm.royalbash.core.exception.GameEngineException;
import de.schramm.royalbash.core.exception.RuleViolationException;

public class PlainDefendingTargetEffect implements DefendingTargetEffect {

    @Override
    public void apply(
            Summoning attackingSummoning,
            AttackableTarget attackedTarget,
            Summoning defendingSummoning
    ) throws GameEngineException {

        if(!attackedTarget.isOccupied() || !attackedTarget.getSummoning().equals(defendingSummoning)) {
            throw new RuleViolationException(String.format(
                    "Defending summoning %s does not occupy attacked target %s",
                    defendingSummoning.getId(),
                    attackedTarget.getId()
            ));
        }

        defendingSummoning.dealDamage(attackingSummoning);
    }
}
