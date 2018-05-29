package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.model.summoningcard.SummoningCard;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@ToString
public class SummoningDeck {

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
