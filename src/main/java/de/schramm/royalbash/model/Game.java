package de.schramm.royalbash.model;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
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

    public Card findHandCard(UUID cardId) throws DomainObjectDoesNotExistException {

        try {

            val playerRedCards = board.getPlayerRed().getHand().getCards();
            val playerBlueCards = board.getPlayerBlue().getHand().getCards();

            return Stream.of(playerRedCards, playerBlueCards)
                    .flatMap(Collection::stream)
                    .filter(card -> card.getId().equals(cardId))
                    .findFirst()
                    .orElseThrow(NullPointerException::new);
        } catch (NullPointerException e) {

            throw new DomainObjectDoesNotExistException(
                    String.format(
                            "Hand Card %s does not exist",
                            cardId
                    )
            );
        }
    }

    public Target findTarget(UUID targetId) throws DomainObjectDoesNotExistException {

        try {

            val playerRedTargets = board.getPlayerRed().getTargets();
            val playerBlueTargets = board.getPlayerBlue().getTargets();

            return Stream.of(playerRedTargets, playerBlueTargets)
                    .flatMap(Collection::stream)
                    .filter(target -> target.getId().equals(targetId))
                    .findFirst()
                    .orElseThrow(NullPointerException::new);
        } catch (NullPointerException e) {

            throw new DomainObjectDoesNotExistException(
                    String.format(
                            "Target %s does not exist",
                            targetId
                    )
            );
        }
    }
}
