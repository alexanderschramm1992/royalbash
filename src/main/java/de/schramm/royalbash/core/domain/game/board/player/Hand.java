package de.schramm.royalbash.core.domain.game.board.player;

import de.schramm.royalbash.core.domain.game.board.player.field.Card;
import de.schramm.royalbash.core.domain.game.board.player.field.ResourcesCard;
import de.schramm.royalbash.core.domain.game.board.player.field.SummoningCard;
import de.schramm.royalbash.core.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.core.exception.RuleViolationException;
import lombok.*;

import java.util.ArrayList;
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
            val newCards = new ArrayList<Card>(cards);
            newCards.remove(card);
            cards = newCards;
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
