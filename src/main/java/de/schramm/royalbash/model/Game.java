package de.schramm.royalbash.model;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.model.resourcescard.ResourcesCard;
import de.schramm.royalbash.model.summoningcard.SummoningCard;
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

    public SummoningCard findHandSummoningCard(UUID cardId) throws DomainObjectDoesNotExistException{

        val card = findHandCard(cardId);
        if (card instanceof SummoningCard) {
            return (SummoningCard) card;
        } else {
            throw new DomainObjectDoesNotExistException(
                    String.format(
                            "Hand Card %s is not a Summoning Card",
                            cardId
                    )
            );
        }
    }

    public ResourcesCard findHandResourcesCard(UUID cardId) throws DomainObjectDoesNotExistException{

        val card = findHandCard(cardId);
        if (card instanceof ResourcesCard) {
            return (ResourcesCard) card;
        } else {
            throw new DomainObjectDoesNotExistException(
                    String.format(
                            "Hand Card %s is not a Resources Card",
                            cardId
                    )
            );
        }
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
