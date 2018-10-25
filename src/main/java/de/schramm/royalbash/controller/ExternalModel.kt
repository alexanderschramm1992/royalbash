package de.schramm.royalbash.controller

import io.swagger.annotations.ApiModelProperty


internal class ExternalModel {

    internal class Game(
            @ApiModelProperty(required = true) val id: String,
            @ApiModelProperty(required = true) val player1: Player,
            @ApiModelProperty(required = true) val player2: Player,
            @ApiModelProperty(required = true) val playerOnTurn: String,
            @ApiModelProperty(required = true) val state: String
    ) {
        companion object {

            fun toExternalModel(game: de.schramm.royalbash.controller.service.core.Game): Game {
                return Game(
                    id = game.id,
                    player1 = Player.toExternalModel(game.player1),
                    player2 = Player.toExternalModel(game.player2),
                    playerOnTurn = game.playerOnTurn.id,
                    state = game.state.name)
            }
        }
    }

    internal class Player(
            @ApiModelProperty(required = true) val id: String,
            @ApiModelProperty(required = true) val name: String = "",
            @ApiModelProperty(required = true) val hitpoints: Int = 0,
            @ApiModelProperty(required = true) val resources: Int = 0,
            @ApiModelProperty(required = true) val deckcards: List<Card> = listOf(),
            @ApiModelProperty(required = true) val handcards: List<Card> = listOf(),
            @ApiModelProperty(required = true) val spots: List<Spot> = listOf()
    ) {

        companion object {

            fun toExternalModel(player: de.schramm.royalbash.controller.service.core.Player): Player {
                return Player(
                        id = player.id,
                        name = player.name,
                        hitpoints = player.hitpoints,
                        resources = player.resources,
                        deckcards = player.deckcards.map { Card.toExternalModel(it) },
                        handcards = player.handcards.map { Card.toExternalModel(it) },
                        spots = player.spots.map {Spot.toExternalModel(it) })
            }
        }
    }

    internal class Card(
            @ApiModelProperty(required = true) val id: String,
            @ApiModelProperty(required = true) val name: String,
            @ApiModelProperty(required = true) val text: String,
            @ApiModelProperty(required = true) val cost: Int = 0,
            @ApiModelProperty(required = true) val placeableOnSpot: Boolean
    ) {

        companion object {

            fun toExternalModel(card: de.schramm.royalbash.controller.service.core.Card): Card {
                return Card(
                        id = card.id,
                        name = card.name,
                        text = card.text,
                        cost = card.cost,
                        placeableOnSpot = card.placeableOnSpot)
            }
        }
    }

    internal class Spot(@ApiModelProperty(required = false) val creature: Creature? = null) {

        companion object {

            fun toExternalModel(spot: de.schramm.royalbash.controller.service.core.Spot): Spot {
                return Spot( creature = spot.getCreature()
                        .map { Creature.toExternalModel(it) }
                        .orElse(null))
            }
        }
    }

    internal class Creature(
            @ApiModelProperty(required = true) val id: String,
            @ApiModelProperty(required = true) val name: String = "",
            @ApiModelProperty(required = true) val hitpoints: Int = 0,
            @ApiModelProperty(required = true) val atttack: Int = 0
    ) {

        companion object {

            fun toExternalModel(creature: de.schramm.royalbash.controller.service.core.Creature): Creature {
                return Creature(
                        id = creature.id,
                        name = creature.name,
                        hitpoints = creature.hitpoints,
                        atttack = creature.attack)
            }
        }
    }
}
