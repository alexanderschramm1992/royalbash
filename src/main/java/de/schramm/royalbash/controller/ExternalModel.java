package de.schramm.royalbash.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
@Builder
class ExternalModel {

    @Value
    @Builder
    static class Game {
        @ApiModelProperty(required = true)
        private Player player1;
        @ApiModelProperty(required = true)
        private Player player2;
        @ApiModelProperty(required = true)
        private String playerOnTurn;
        @ApiModelProperty(required = true)
        private String state;

        static Game toExternalModel(de.schramm.royalbash.controller.service.core.Game game) {
            return Game.builder()
                    .player1(Player.toExternalModel(game.getPlayer1()))
                    .player2(Player.toExternalModel(game.getPlayer2()))
                    .playerOnTurn(game.getPlayerOnTurn().getId())
                    .state(game.getState().name())
                    .build();
        }
    }

    @Value
    @Builder
    static class Player {
        @ApiModelProperty(required = true)
        private String id;
        private String name;
        @ApiModelProperty(required = true)
        private int hitpoints;
        @ApiModelProperty(required = true)
        private int resources;
        @ApiModelProperty(required = true)
        @Singular("deckcard")
        private List<Card> deckcards;
        @ApiModelProperty(required = true)
        @Singular("handcard")
        private List<Card> handcards;
        @ApiModelProperty(required = true)
        @Singular("spot")
        private List<Spot> spots;

        static Player toExternalModel(de.schramm.royalbash.controller.service.core.Player player) {
            return Player.builder()
                    .id(player.getId())
                    .name(player.getName())
                    .hitpoints(player.getHitpoints())
                    .resources(player.getResources())
                    .deckcards(player.getDeckcards()
                            .map(Card::toExternalModel)
                            .collect(Collectors.toList()))
                    .handcards(player.getHandcards()
                            .map(Card::toExternalModel)
                            .collect(Collectors.toList()))
                    .spots(player.getSpots()
                        .map(Spot::toExternalModel)
                        .collect(Collectors.toList()))
                    .build();
        }
    }

    @Value
    @Builder
    static class Card {
        @ApiModelProperty(required = true)
        private String id;
        @ApiModelProperty(required = true)
        private int cost;
        @ApiModelProperty(required = true)
        private boolean placeableOnSpot;

        static Card toExternalModel(de.schramm.royalbash.controller.service.core.Card card) {
            return Card.builder()
                    .id(card.getId())
                    .cost(card.getCost())
                    .placeableOnSpot(card.isPlaceableOnSpot())
                    .build();
        }
    }

    @Value
    @Builder
    static class Spot {
        private Creature creature;

        static Spot toExternalModel(de.schramm.royalbash.controller.service.core.Spot spot) {
            return Spot.builder()
                    .creature(spot
                            .getCreature()
                            .map(Creature::toExternalModel)
                            .orElse(null))
                    .build();
        }
    }

    @Value
    @Builder
    static class Creature {
        @ApiModelProperty(required = true)
        private String id;
        private String name;
        @ApiModelProperty(required = true)
        private int hitpoints;
        @ApiModelProperty(required = true)
        private int atttack;

        static Creature toExternalModel(de.schramm.royalbash.controller.service.core.Creature creature) {
            return Creature.builder()
                    .id(creature.getId())
                    .name(creature.getName())
                    .hitpoints(creature.getHitpoints())
                    .atttack(creature.getAttack())
                    .build();
        }
    }
}
