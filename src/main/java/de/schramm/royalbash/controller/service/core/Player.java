package de.schramm.royalbash.controller.service.core;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.val;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Value
@Builder(toBuilder = true)
public class Player {

    private final String id;
    private final String name;
    private final int hitpoints;
    private final int resources;
    @Singular("handcard")
    private final List<Card> handcards;
    @Singular("deckcard")
    private final List<Card> deckcards;
    @Singular("depositcard")
    private final List<Card> depositcards;
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

    public Stream<Card> getHandcards() {
        return handcards.stream();
    }

    public Stream<Card> getDeckcards() {
        return deckcards.stream();
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

    public Player discardCards(int amountOfCards) {

        Player player = this;
        for(int iterator = 0; iterator < amountOfCards; iterator++) {
            player = player.discardCard();
        }

        return player;
    }

    public Player drawCards(int amountOfCards) {

        Player player = this;
        for(int iterator = 0; iterator < amountOfCards; iterator++) {
            player = player.drawCard();
        }

        return player;
    }

    public Optional<Card> findHandcard(String cardId) {
        return getHandcards()
                .filter(card -> cardId.equals(card.getId()))
                .findFirst();
    }

    Player removeHandcard(Card handcard) {

        val handcards = getHandcards()
                .filter(ownHandcard -> !ownHandcard.equals(handcard))
                .collect(Collectors.toList());

        return findHandcard(handcard)
                .map(card -> this.toBuilder()
                        .depositcard(card)
                        .clearHandcards()
                        .handcards(handcards)
                        .build())
                .orElse(this);
    }

    Player removeCreature(Creature creature) {

        val spots = getSpots()
                .map(spot -> spot.removeCreature(creature))
                .collect(Collectors.toList());

        return findCreature(creature)
                .map(ownedCreature -> this.toBuilder()
                        .depositcard(ownedCreature)
                        .clearSpots()
                        .spots(spots)
                        .build())
                .orElse(this);
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

    Player reduceResources(int cost) {
        return this.toBuilder()
                .resources(resources - cost)
                .build();
    }

    Stream<Card> getDepositcards() { return depositcards.stream(); }

    boolean hasCard(Card card) {
        return getHandcards()
                .anyMatch(handcard -> handcard.equals(card));
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

    private Player discardCard() {

        if(handcards.isEmpty()) {
            return this;
        }

        return this.toBuilder()
                .clearHandcards()
                .handcards(handcards.subList(1, handcards.size()))
                .build();
    }

    private Optional<Card> findHandcard(Card card) {
        return getHandcards()
                .filter(card::equals)
                .findFirst();
    }

    private Optional<Creature> findCreature(final Creature creature) {
        return getSpots()
                .map(Spot::getCreature)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(creature::equals)
                .findFirst();
    }
}
