package de.schramm.royalbash.controller.service.core;

public interface Creature extends Card {

    int getHitpoints();
    int getAttack();

    Creature damage(int amountOfDamage);

    boolean isDead();
}
