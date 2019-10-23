package de.schramm.royalbash.domain.card

import de.schramm.royalbash.application.UUIDGenerator
import de.schramm.royalbash.domain.*

fun Game.logOwnerMissing(uuidGenerator: UUIDGenerator, card: Card) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to missing owner")

fun Game.logPlayerNotOnTurn(uuidGenerator: UUIDGenerator, card: Card, owner: Player) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to ${owner.name} not being on turn")

fun Game.logOpponentMissing(uuidGenerator: UUIDGenerator, card: Card) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to missing opponent")

fun Game.logCreatureOwnerMissing(uuidGenerator: UUIDGenerator, card: Card) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to missing target creature owner")

fun Game.logTargetCreatureMissing(uuidGenerator: UUIDGenerator, card: Card) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to missing target creature")

fun Game.logTargetSpotMissing(uuidGenerator: UUIDGenerator, card: Card) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to missing target spot")

fun Game.logTargetSpotOccupied(uuidGenerator: UUIDGenerator, card: Card) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to occupied traget spot")

fun Game.logResourcesMissing(uuidGenerator: UUIDGenerator, card: Card) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to missing resources")

fun Game.logHandcardMissing(uuidGenerator: UUIDGenerator, card: Card, owner: Player) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to card missing in ${owner.name}'s handcards")

fun Game.logInvokationOnSpot(uuidGenerator: UUIDGenerator, card: Card, owner: Player) =
        log(uuidGenerator, "Invoked ${card.name} on ${owner.name}'s spot")

fun Game.logInvokationOnPlayer(uuidGenerator: UUIDGenerator, card: Card, targetPlayer: Player) =
        log(uuidGenerator, "Invoked ${card.name} on ${targetPlayer.name}")

fun Game.logDiscardEffect(uuidGenerator: UUIDGenerator, card: Card, targetPlayer: Player, amount: Int) =
        log(uuidGenerator, "${card.name} made ${targetPlayer.name} discard $amount card")

fun Game.logDrawEffect(uuidGenerator: UUIDGenerator, card: Card, tragetPlayer: Player, amount: Int) =
        log(uuidGenerator, "${card.name} made ${tragetPlayer.name} draw $amount cards")

fun Game.logGainResourcesEffect(uuidGenerator: UUIDGenerator, card: Card, targetPlayer: Player, amount: Int) =
        log(uuidGenerator, "${card.name} made ${targetPlayer.name} gain $amount resources")

fun Game.logDamageOnPlayer(uuidGenerator: UUIDGenerator, card: Card, targetPlayer: Player, amount: Int) =
        log(uuidGenerator, "${card.name} dealt $amount damage to ${targetPlayer.name}")

fun Game.logDamageOnCreature(uuidGenerator: UUIDGenerator, card: Card, targetCreature: Creature, targetOwner: Player,
                             amount: Int) =
        log(uuidGenerator, "${card.name} dealt $amount damage to ${targetCreature.name} of ${targetOwner.name}")
