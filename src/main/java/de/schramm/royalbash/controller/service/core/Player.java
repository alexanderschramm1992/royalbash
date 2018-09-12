package de.schramm.royalbash.controller.service.core;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.val;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Value
@Builder(toBuilder = true)
public class Player {

    private final String name;
    private final int hitpoints;
    private final int resources;
    @Singular("handcard")
    private final List<Card> handcards;
    @Singular("deckcard")
    private final List<Card> deckcards;
    @Singular("spot")
    private final List<Spot> spots;

    public Player setHitpoints(int hitpoints) {
        return Player.builder()
                .hitpoints(hitpoints)
                .build();
    }

    public Stream<Spot> getSpots() {
        return spots.stream();
    }

    Player removeHandcard(Card handcard) {

        val handcards = this.handcards.stream()
                .filter(ownHandcard -> !ownHandcard.equals(handcard))
                .collect(Collectors.toList());

        return this.toBuilder()
                .clearHandcards()
                .handcards(handcards)
                .build();
    }

    Player removeCreature(Creature creature) {

        val spots = getSpots()
                .map(spot -> spot.removeCreature(creature))
                .collect(Collectors.toList());

        return this.toBuilder()
                .clearSpots()
                .spots(spots)
                .build();
    }

    Player updateCreature(Creature oldCreature, Creature newCreature) {

        val spots = getSpots()
                .map(spot -> spot.updateCreature(oldCreature, newCreature))
                .collect(Collectors.toList());

        return this.toBuilder()
                .clearSpots()
                .spots(spots)
                .build();
    }

    boolean hasCard(Card card) {
        return handcards.stream()
                .anyMatch(handcard -> handcard.equals(card));
    }

    public Player drawCards(int amountOfCards) {

        Player player = this;
        for(int iterator = 0; iterator < amountOfCards; iterator++) {
            player = player.drawCard();
        }

        return player;
    }

    private Player drawCard() {

        if(deckcards.isEmpty()) {
            return this;
        }

        return this.toBuilder()
                .clearDeckcards()
                .deckcards(deckcards.subList(1, deckcards.size()))
                .handcard(deckcards.get(0))
                .build();
    }

    public Player discardCards(int amountOfCards) {

        Player player = this;
        for(int iterator = 0; iterator < amountOfCards; iterator++) {
            player = player.discardCard();
        }

        return player;
    }

    private Player discardCard() {

        if(handcards.isEmpty()) {
            return this;
        }

        return this.toBuilder()
                .clearHandcards()
                .handcards(handcards.subList(1, handcards.size()))
                .build();
    }

    public Player updateSpot(Spot oldSpot, Spot newSpot) {

        val spots = this.spots.stream()
                .map(spot -> spot.equals(oldSpot) ? newSpot : spot)
                .collect(Collectors.toList());

        return this.toBuilder()
                .clearSpots()
                .spots(spots)
                .build();
    }

    Player reduceResources(int cost) {
        return this.toBuilder()
                .resources(resources - cost)
                .build();
    }
}
