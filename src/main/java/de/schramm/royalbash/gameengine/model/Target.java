package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class Target {

    private UUID id;
    private Summoning summoning;

    void summon(Summoning summoning) throws RuleViolationException {

        if(this.summoning == null) {
            this.summoning = summoning;
        } else {
            throw new RuleViolationException(String.format("Target %s is occupied", id));
        }
    }

    void bury(Summoning summoning) {
        if (summoning.equals(this.summoning)) {
            this.summoning = null;
        }
    }
}
