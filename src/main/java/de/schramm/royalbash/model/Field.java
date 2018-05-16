package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class Field {

    @Singular("target")
    private List<Target> targets;

    public void summon(Summoning summoning, Target target) {

        targets.stream()
                .filter(element -> element.equals(target))
                .forEach(element -> element.summon(summoning));
    }

    public void bury(Summoning summoning) {

        targets.stream()
                .filter(target -> summoning.equals(target.getSummoning()))
                .forEach(target -> target.bury(summoning));
    }

    public Target getTarget(UUID targetId) {

        return targets.stream()
                .filter(target -> target.getId().equals(targetId))
                .findFirst()
                .orElse(null);
    }
}
