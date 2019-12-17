package de.schramm.royalbash.application

import de.schramm.royalbash.api.ExternalModel
import de.schramm.royalbash.domain.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun Game.toExternalModel() = ExternalModel.Game(
        id = id,
        player1 = player1.toExternalModel(),
        player2 = player2.toExternalModel(),
        state = state.name,
        turns = turns.map { it.toExternalModel() },
        logs = logs.map { it.toExternalModel() })

fun Turn.toExternalModel() = ExternalModel.Turn(
        playerOnTurn = playerOnTurn.id,
        cardDrawn = cardDrawn)

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
        instanceId = instanceId,
        name = name,
        text = text,
        cost = cost,
        attack = if (this is Creature) attack else null,
        hitpoints = if (this is Creature) hitpoints else null,
        image = image)

fun Spot.toExternalModel() = ExternalModel.Spot(
        id = id,
        creature = creature?.toExternalModel())

fun Creature.toExternalModel() = ExternalModel.Creature(
        id = id,
        instanceId = instanceId,
        name = name,
        text = text,
        cost = cost,
        hitpoints = hitpoints,
        attack = attack,
        image = image)

fun Log.toExternalModel() = ExternalModel.Log(
        id = id,
        timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
        text = text)
