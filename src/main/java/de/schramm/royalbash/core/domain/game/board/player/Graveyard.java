package de.schramm.royalbash.core.domain.game.board.player;

import de.schramm.royalbash.core.domain.game.board.player.field.Card;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
class Graveyard {

    @Singular("card")
    private final List<Card> cards;
}
