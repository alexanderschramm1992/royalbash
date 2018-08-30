package de.schramm.royalbash.tdd.core.card;

import de.schramm.royalbash.tdd.core.Card;
import de.schramm.royalbash.tdd.core.Context;
import de.schramm.royalbash.tdd.core.Game;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NoOpCard implements Card {

    private final String name;

    @Override
    public Game invoke(Context context) {
        return context.getGame();
    }
}
