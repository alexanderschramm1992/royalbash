package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.gameengine.exception.GameBrokenException;
import lombok.Builder;
import lombok.Value;
import lombok.val;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Stream;

@Value
@Builder
public class Game {

    @Id
    private UUID id;
    private UUID accountRed;
    private UUID accountBlue;
    private Board board;

    public Player findPlayer(UUID playerId) throws DomainObjectDoesNotExistException{

        try {

            return board.getPlayerRed().getId().equals(playerId) ? board.getPlayerRed() : board.getPlayerBlue();
        } catch(NullPointerException e) {

            throw new DomainObjectDoesNotExistException(
                    String.format(
                            "Player %s does not exist",
                            playerId
                    )
            );
        }
    }

    public Player findOpponent(Player player) throws GameBrokenException {
        return board.findOpponent(player);
    }

    public Target findTarget(UUID targetId) throws DomainObjectDoesNotExistException {

        try {

            val playerRedTargets = board.getPlayerRed().getField().getTargets();
            val playerBlueTargets = board.getPlayerBlue().getField().getTargets();

            return Stream.of(playerRedTargets, playerBlueTargets)
                    .flatMap(Collection::stream)
                    .filter(target -> target.getId().equals(targetId))
                    .findFirst()
                    .orElseThrow(NullPointerException::new);
        } catch(NullPointerException e) {
            throw new DomainObjectDoesNotExistException(String.format("Target %s does not exist",targetId));
        }
    }
}
