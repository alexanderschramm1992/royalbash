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
    private List<SummoningCard> summoningCards;

    public void addCard(SummoningCard summoningCard) {

        List<SummoningCard> list = new ArrayList<>(summoningCards);

        list.add(summoningCard);

        summoningCards = list;
    }

    public void removeCard(SummoningCard summoningCard) {

        List<SummoningCard> list = new ArrayList<>(summoningCards);

        list.remove(summoningCard);

        summoningCards = list;
    }
}
