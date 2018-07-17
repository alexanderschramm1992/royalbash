package de.schramm.royalbash.core.domain;

import de.schramm.royalbash.core.domain.card.summoningcard.SummoningCard;
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
