package de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget;

import de.schramm.royalbash.core.exception.RuleViolationException;

import java.util.UUID;

public interface FightableSummoning {

    boolean hasTag(Tag tag);
    void dealDamage(FightableSummoning attackedSummoning);
    void setCanAttack(boolean canAttack);
    void increaseCurrentStrength(int amount);
    void reduceCurrentStrength(int amount);
    int getCurrentHealth();
    void setCurrentHealth(int amount);
    void defendTarget(FightableSummoning attackingSummoning, AttackableTarget attackedTarget) throws RuleViolationException;
    UUID getId();
}
