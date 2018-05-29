package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.model.summoningcard.SummoningCard;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class Graveyard {

    @Singular("card")
    private List<SummoningCard> summoningCards;
}
