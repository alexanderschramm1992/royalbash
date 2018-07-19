package de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget;

import java.util.UUID;

public interface AttackableTarget {

    boolean isOccupied();
    FightableSummoning getSummoning();
    UUID getId();
}
