package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.model.summoningcard.SummoningCard;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class Blueprint {

    private final UUID id;
    private String name;

    @Singular("card")
    private List<SummoningCard> summoningCards;
}
