package de.schramm.royalbash.core.domain.game.board.player.field;

import de.schramm.royalbash.core.exception.RuleViolationException;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class Target {

    private UUID id;
    private Summoning summoning;

    public boolean isOccupied() {
        return summoning != null;
    }

    void summon(Summoning summoning) throws RuleViolationException {

        if(this.summoning == null) {
            this.summoning = summoning;
        } else {
            throw new RuleViolationException(String.format("Target %s is occupied", id));
        }
    }

    void bury(Summoning summoning) throws RuleViolationException {
        if (summoning.equals(this.summoning)) {
            this.summoning = null;
        } else {
            throw new RuleViolationException(String.format(
                    "Target %s not occupied by Summoning %s",
                    id,
                    summoning.getId()
            ));
        }
    }

    public void purge() {

        if(isOccupied() && summoning.isDead()) {
            summoning = null;
        }
    }
}
