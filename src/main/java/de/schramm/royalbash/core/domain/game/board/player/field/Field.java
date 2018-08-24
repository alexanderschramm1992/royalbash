package de.schramm.royalbash.core.domain.game.board.player.field;

import de.schramm.royalbash.core.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.core.exception.GameEngineException;
import de.schramm.royalbash.core.exception.RuleViolationException;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
@Builder
public class Field {

    @Singular("target")
    private List<Target> targets;

    public Field summon(Summoning summoning, Target target) throws GameEngineException {

        if(!targets.contains(target)) {
            throw new DomainObjectDoesNotExistException(
                    String.format("Target %s does not exist on field", target.getId())
            );
        } else if (target.isOccupied()) {
            throw new RuleViolationException(
                    String.format("Target %s is already occupied", target.getId())
            );
        } else {
            val targets = new ArrayList<Target>(this.targets);
            targets.remove(target);
            targets.add(target.summon(summoning));
            return new Field(targets);
        }
    }

    public Field bury(Summoning summoning) throws DomainObjectDoesNotExistException {

        val target = targets.stream()
                .filter(ownTarget -> summoning.equals(ownTarget.getSummoning()))
                .findFirst()
                .orElseThrow(() -> new DomainObjectDoesNotExistException(String.format(
                        "Cannot find Target occupied by Summoning %s",
                        summoning.getId()
                )));

        val targets = this.targets;
        targets.remove(target);
        targets.add(target.bury(summoning));
        return new Field(targets);
    }

    public Target getTarget(UUID targetId) throws DomainObjectDoesNotExistException {

        return targets.stream()
                .filter(target -> target.getId().equals(targetId))
                .findFirst()
                .orElseThrow(() -> new DomainObjectDoesNotExistException(
                        String.format("Cannot find target %s", targetId)
                ));
    }

    public Field purge() {

        val targets = this.targets.stream()
                .map(Target::purge)
                .collect(Collectors.toList());

        return new Field(targets);
    }
}
