package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import de.schramm.royalbash.gameengine.model.card.resourcescard.ResourcesCard;
import de.schramm.royalbash.gameengine.model.card.summoningcard.SummoningCard;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@ToString
public class Hand {

    private static final int MAX = 5;

    @Singular("card")
    private List<Card> cards;

    void addCard(Card card) throws RuleViolationException {

        if(cards.size() < MAX) {
            cards.add(card);
        } else {
            throw new RuleViolationException("Cannot draw another card");
        }
    }

    void removeCard(Card card) throws RuleViolationException {

        if (cards.contains(card)) {
            cards.remove(card);
        } else {
            throw new RuleViolationException(String.format("Card %s not in hand", card.getId()));
        }
    }

    public SummoningCard findSummoningCard(UUID cardId) throws DomainObjectDoesNotExistException {

        val card = findHandCard(cardId);
        if (card instanceof SummoningCard) {
            return (SummoningCard) card;
        } else {
            throw new DomainObjectDoesNotExistException(
                    String.format("Hand Card %s is not a Summoning Card", cardId)
            );
        }
    }

    public ResourcesCard findResourcesCard(UUID cardId) throws DomainObjectDoesNotExistException{

        val card = findHandCard(cardId);
        if (card instanceof ResourcesCard) {
            return (ResourcesCard) card;
        } else {
            throw new DomainObjectDoesNotExistException(
                    String.format("Hand Card %s is not a Resources Card", cardId)
            );
        }
    }

    private Card findHandCard(UUID cardId) throws DomainObjectDoesNotExistException {

        return cards.stream()
                .filter(card -> card.getId().equals(cardId))
                .findFirst()
                .orElseThrow(() -> new DomainObjectDoesNotExistException(
                        String.format("Card %s cannot be found in hand", cardId))
                );
    }
}
