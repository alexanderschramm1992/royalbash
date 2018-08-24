package de.schramm.royalbash.core.domain.game.board.player.field;

import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.AttackableTarget;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Target implements AttackableTarget {

    private final UUID id;
    private final Summoning summoning;

    public boolean isOccupied() {
        return summoning != null;
    }

    Target summon(Summoning summoning) {
        return new Target(id, summoning);
    }

    Target bury(Summoning summoning) {
        return new Target(id, null);
    }

    Target purge() {
        return isOccupied() && summoning.isDead() ? new Target(id, null) : this;
    }
}
