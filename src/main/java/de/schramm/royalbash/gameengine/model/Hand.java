package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class Hand {

    private final static int MAX = 5;

    @Singular("card")
    private List<Card> cards;

    public void addCard(Card card) throws GameRuleViolationException {

        if(cards.size() < MAX) {
            cards.add(card);
        } else {
            throw new GameRuleViolationException("Cannot draw another card");
        }
    }

    public void removeCard(Card card) throws GameRuleViolationException {

        if (cards.contains(card)) {
            cards.remove(card);
        } else {
            throw new GameRuleViolationException(String.format("Card %s not in hand", card.getId()));
        }
    }
}
