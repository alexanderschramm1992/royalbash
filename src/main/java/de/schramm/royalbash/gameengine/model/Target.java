package de.schramm.royalbash.gameengine.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class Target {

    private UUID id;
    private Summoning summoning;

    public void summon(Summoning summoning) {
        this.summoning = summoning;
    }

    public void bury(Summoning summoning) {
        if (summoning.equals(this.summoning)) {
            this.summoning = null;
        }
    }
}
