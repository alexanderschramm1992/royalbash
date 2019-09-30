package de.schramm.royalbash.api

import io.swagger.annotations.ApiModelProperty

sealed class ExternalModel {

    data class Game(
            @ApiModelProperty(required = true) val id: String,
            @ApiModelProperty(required = true) val player1: Player,
            @ApiModelProperty(required = true) val player2: Player,
            @ApiModelProperty(required = true) val playerOnTurn: String,
            @ApiModelProperty(required = true) val state: String,
            @ApiModelProperty(required = true) val logs: List<Log>): ExternalModel()

    data class Player(
            @ApiModelProperty(required = true) val id: String,
            @ApiModelProperty(required = true) val name: String,
            @ApiModelProperty(required = true) val hitpoints: Int,
            @ApiModelProperty(required = true) val resources: Int,
            @ApiModelProperty(required = true) val deckcards: List<Card> = listOf(),
            @ApiModelProperty(required = true) val handcards: List<Card> = listOf(),
            @ApiModelProperty(required = true) val spots: List<Spot> = listOf()): ExternalModel()

    data class Card(
            @ApiModelProperty(required = true) val id: String,
            @ApiModelProperty(required = true) val instanceId: String,
            @ApiModelProperty(required = true) val name: String,
            @ApiModelProperty(required = true) val text: String,
            @ApiModelProperty(required = true) val cost: Int,
            @ApiModelProperty(required = true) val placeableOnSpot: Boolean,
            @ApiModelProperty(required = false) val image: String): ExternalModel()

    data class Spot(
            @ApiModelProperty(required = false) val id: String,
            @ApiModelProperty(required = false) val creature: Creature? = null): ExternalModel()

    data class Creature(
            @ApiModelProperty(required = true) val id: String,
            @ApiModelProperty(required = true) val instanceId: String,
            @ApiModelProperty(required = true) val name: String,
            @ApiModelProperty(required = true) val text: String,
            @ApiModelProperty(required = true) val hitpoints: Int,
            @ApiModelProperty(required = true) val attack: Int,
            @ApiModelProperty(required = false) val image: String): ExternalModel()

    data class Log(
            @ApiModelProperty(required = true) val id: String,
            @ApiModelProperty(required = true) val timestamp: String,
            @ApiModelProperty(required = true) val text: String): ExternalModel()
}
