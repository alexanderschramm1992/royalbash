package de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget;

import de.schramm.royalbash.core.domain.game.board.player.field.Summoning;

import java.util.UUID;

public interface AttackableTarget {

    boolean isOccupied();
    Summoning getSummoning();
    UUID getId();
}
