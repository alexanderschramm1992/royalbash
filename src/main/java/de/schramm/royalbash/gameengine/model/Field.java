package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
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

    void summon(Summoning summoning, Target target) throws DomainObjectDoesNotExistException {

        targets.stream()
                .filter(element -> element.equals(target))
                .findFirst()
                .orElseThrow(() -> new DomainObjectDoesNotExistException(
                        String.format("Target %s does not exist on field", target.getId())
                )).summon(summoning);
    }

    void bury(Summoning summoning) {

        targets.stream()
                .filter(target -> summoning.equals(target.getSummoning()))
                .forEach(target -> target.bury(summoning));
    }

    Target getTarget(UUID targetId) {

        return targets.stream()
                .filter(target -> target.getId().equals(targetId))
                .findFirst()
                .orElse(null);
    }
}
