package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@ToString
public class Deck {

    private final UUID id;

    @Singular("card")
    private List<SummoningCard> summoningCards;

    public void shuffle() {

        Collections.shuffle(summoningCards);
    }

    public SummoningCard drawCard() {

        return summoningCards.isEmpty() ? null : summoningCards.remove(0);
    }
}
