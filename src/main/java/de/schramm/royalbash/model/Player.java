package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Builder
@Getter
@ToString
public class Player {

    private final UUID id;
    private final UUID accountId;
    private final Deck summoningDeck;
    private final Deck resourcesDeck;
    private final Hand hand;
    private final Graveyard graveyard;
    private final Field field;

    private int health;

    public void summon(Summoning summoning, Target target) {

        field.summon(summoning, target);
    }

    public void bury(Summoning summoning) {

        field.bury(summoning);
    }

    public void drawCard() {

        hand.addCard(summoningDeck.drawCard());
    }

    public Target getTarget(UUID targetId) {

        return field.getTarget(targetId);
    }
}
