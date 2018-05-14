package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.*;

@Builder
@Getter
@ToString
public class Player {

    private final UUID id;
    private final UUID accountId;
    private final Deck deck;

    private int health;

    @Singular("card")
    private List<Card> cards;

    @Singular("target")
    private List<Target> targets;

    public void addCard(Card card) {

        List<Card> list = new ArrayList<>(cards);

        list.add(card);

        cards = list;
    }

    public void removeCard(Card card) {

        List<Card> list = new ArrayList<>(cards);

        list.remove(card);

        cards = list;
    }

    public void summon(Summoning summoning, Target target) {

        targets.stream()
                .filter(element -> element.equals(target))
                .forEach(element -> element.summon(summoning));
    }

    public void bury(Summoning summoning) {

        targets.stream()
                .filter(target -> summoning.equals(target.getSummoning()))
                .forEach(target -> target.bury(summoning));
    }

    public Target getTarget(UUID targetId) {

        return targets.stream()
                .filter(target -> target.getId().equals(targetId))
                .findFirst()
                .orElse(null);
    }
}
