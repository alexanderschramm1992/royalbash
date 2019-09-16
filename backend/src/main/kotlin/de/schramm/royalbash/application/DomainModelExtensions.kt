package de.schramm.royalbash.application

import de.schramm.royalbash.api.ExternalModel
import de.schramm.royalbash.domain.*

fun Game.toExternalModel() = ExternalModel.Game(
        id = id,
        player1 = player1.toExternalModel(),
        player2 = player2.toExternalModel(),
        playerOnTurn = playerOnTurn.id,
        state = state.name)

fun Player.toExternalModel() = ExternalModel.Player(
        id = id,
        name = name,
        hitpoints = hitpoints,
        resources = resources,
        deckcards = deckcards.map { it.toExternalModel() },
        handcards = handcards.map { it.toExternalModel() },
        spots = spots.map { it.toExternalModel() })

fun Card.toExternalModel() = ExternalModel.Card(
        id = id,
        name = name,
        text = text,
        cost = cost,
        placeableOnSpot = placeableOnSpot,
        image = image)

fun Spot.toExternalModel() = ExternalModel.Spot(
        id = id,
        creature = creature?.toExternalModel())

fun Creature.toExternalModel() = ExternalModel.Creature(
        id = id,
        name = name,
        text = text,
        hitpoints = hitpoints,
        attack = attack,
        image = image)
