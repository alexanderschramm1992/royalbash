package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.*;

@Builder
@Getter
public class Player implements AttackableCanAttack {

    private final UUID id;
    private final Account account;
    private final Deck deck;

    private int health;

    @Singular("card")
    private List<Card> cards;

    @Singular("summoning")
    private List<Summoning> summonings;

    @Override
    public int getCurrentStrength() {
        return 0; // Players have no attack damage
    }

    @Override
    public boolean isDead() {

        return health <= 0;
    }

    @Override
    public void receiveDamage(AttackableCanAttack attackingInstance) {

        health -= attackingInstance.getCurrentStrength();
    }

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

    public void summon(Summoning summoning) {

        List<Summoning> list = new ArrayList<>(summonings);

        list.add(summoning);

        summonings = list;
    }

    public void bury(Summoning summoning) {

        List<Summoning> list = new ArrayList<>(summonings);

        list.remove(summoning);

        summonings = list;
    }
}
