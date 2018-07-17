package de.schramm.royalbash.core.domain.game.board.player.field;

import de.schramm.royalbash.core.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.core.exception.GameEngineException;
import de.schramm.royalbash.core.exception.RuleViolationException;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.val;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class Field {

    @Singular("target")
    private List<Target> targets;

    public void summon(Summoning summoning, Target target) throws GameEngineException {

        targets.stream()
                .filter(element -> element.equals(target))
                .findFirst()
                .orElseThrow(() -> new DomainObjectDoesNotExistException(
                        String.format("Target %s does not exist on field", target.getId())
                )).summon(summoning);
    }

    public void bury(Summoning summoning) throws DomainObjectDoesNotExistException, RuleViolationException {

        val targetOptional = targets.stream()
                .filter(target -> summoning.equals(target.getSummoning()))
                .findFirst();

        if(targetOptional.isPresent()) {

            targetOptional.get().bury(summoning);
        } else {

            throw new DomainObjectDoesNotExistException(String.format(
                    "Cannot find Target occupied by Summoning %s",
                    summoning.getId()
            ));
        }
    }

    public Target getTarget(UUID targetId) throws DomainObjectDoesNotExistException {

        return targets.stream()
                .filter(target -> target.getId().equals(targetId))
                .findFirst()
                .orElseThrow(() -> new DomainObjectDoesNotExistException(
                        String.format("Cannot find target %s", targetId)
                ));
    }
}
