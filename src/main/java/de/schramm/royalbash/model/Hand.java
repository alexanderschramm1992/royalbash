package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString
public class Hand {

    @Singular("card")
    private List<Card> cards;

    public void addCard(Card card) {

        List<Card> list = new ArrayList<>(cards);

        list.add(card);

        cards = list;
    }

    public void removeCard(Card card) {

        List<Card> list = new ArrayList<>(cards);

        list.remove(card);

        cards = list;
    }
}
