package de.schramm.royalbash.tdd.core;

public interface Creature extends Card {

    int getHitpoints();
    int getAttack();

    Creature damage(Creature attacker);

    boolean isDead();
}
