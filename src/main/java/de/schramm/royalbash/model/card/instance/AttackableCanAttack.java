package de.schramm.royalbash.model.card.instance;

public interface AttackableCanAttack extends CardInstance {

    int getCurrentStrength();

    boolean isDead();

    void receiveDamage(AttackableCanAttack attackingInstance);
}
