package de.schramm.royalbash.core.domain.game.board.player;

import de.schramm.royalbash.core.domain.card.summoningcard.SummoningCard;
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
