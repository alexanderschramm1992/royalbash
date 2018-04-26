package de.schramm.royalbash.model;

public interface AttackableCanAttack {

    int getCurrentStrength();

    boolean isDead();

    void receiveDamage(AttackableCanAttack attackingInstance);
}
