package de.schramm.royalbash.domain.card

import de.schramm.royalbash.domain.*

fun Game.logCardInstanceMissing(uuidGenerator: UUIDGenerator) =
        log(uuidGenerator, "Card to invoke is missing")

fun Game.logOwnerOfCardMissing(uuidGenerator: UUIDGenerator, card: Card) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to missing owner")

fun Game.logOwnerOfAttackerMissing(uuidGenerator: UUIDGenerator, attacker: Creature) =
        log(uuidGenerator, "${attacker.name} cannot attack due to missing owner")

fun Game.logOwnerOfDefenderMissing(uuidGenerator: UUIDGenerator, attacker: Creature, defender: Creature) =
        log(uuidGenerator, "${attacker.name} cannot attack due to missing owner of defender ${defender.name}")

fun Game.logOwnerOfCardNotOnTurn(uuidGenerator: UUIDGenerator, card: Card, owner: Player) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to ${owner.name} not being on turn")

fun Game.logOwnerOfAttackerNotOnTurn(uuidGenerator: UUIDGenerator, attacker: Creature, owner: Player) =
        log(uuidGenerator, "${attacker.name} cannot attack due to ${owner.name} not being on turn")

fun Game.logOwnerIsDefender(uuidGenerator: UUIDGenerator, attacker: Creature, owner: Player) =
        log(uuidGenerator, "${attacker.name} cannot attack due to defender being its owner ${owner.name}")

fun Game.logCardNotInOwnerHandcards(uuidGenerator: UUIDGenerator, card: Card, owner: Player) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to it not being a handcard of ${owner.name}")

fun Game.logOpponentMissing(uuidGenerator: UUIDGenerator, card: Card) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to missing opponent")

fun Game.logDefenderIsNotOpponent(uuidGenerator: UUIDGenerator, attacker: Creature, owner: Player, defender: Player) =
        log(uuidGenerator, "${attacker.name} cannot attack due to defender " +
                           "${defender.name} not being opponent of ${owner.name}")

fun Game.logCreatureOwnerMissing(uuidGenerator: UUIDGenerator, card: Card) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to missing target creature owner")

fun Game.logAttackerMissing(uuidGenerator: UUIDGenerator) =
        log(uuidGenerator, "Cannot commit attack due to missing attacker")

fun Game.logDefenderMissing(uuidGenerator: UUIDGenerator, attacker: Creature) =
        log(uuidGenerator, "${attacker.name} cannot attack due to missing defender")

fun Game.logAttackerNotOnOwnerSpots(uuidGenerator: UUIDGenerator, attacker: Creature, owner: Player) =
        log(uuidGenerator, "${attacker.name} cannot attack due to not being on one of ${owner.name}'s spots")

fun Game.logDefenderNotOnOwnerSpots(uuidGenerator: UUIDGenerator,
                                    attacker: Creature,
                                    defender: Creature,
                                    defenderOwner: Player) =
        log(uuidGenerator, "${attacker.name} cannot attack due to defender ${defender.name} " +
                           "not being on one of ${defenderOwner.name}'s spots")

fun Game.logTargetPlayerNotOwner(uuidGenerator: UUIDGenerator, card: Card, target: Player, owner: Player) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to target player " +
                           "${target.name} not being owner ${owner.name}")

fun Game.logTargetPlayerIsOwner(uuidGenerator: UUIDGenerator, card: Card, target: Player) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to target ${target.name} being its owner")

fun Game.logTargetPlayerMissing(uuidGenerator: UUIDGenerator, card: Card) =
        log(uuidGenerator, "Cannot invoke ${card.name} due to missing target player")

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

fun Game.logCannotInvokeOnCreature(uuidGenerator: UUIDGenerator, card: Card) =
        log(uuidGenerator, "Cannot invoke ${card.name} on a creature")

fun Game.logCannotInvokeOnSpot(uuidGenerator: UUIDGenerator, card: Card) =
        log(uuidGenerator, "Cannot invoke ${card.name} on a spot")

fun Game.logCannotInvokeOnPlayer(uuidGenerator: UUIDGenerator, card: Card) =
        log(uuidGenerator, "Cannot invoke ${card.name} on a player")

fun Game.logInvokationOnSpot(uuidGenerator: UUIDGenerator, card: Card, owner: Player) =
        log(uuidGenerator, "Invoked ${card.name} on ${owner.name}'s spot")

fun Game.logInvokationOnCreature(uuidGenerator: UUIDGenerator, card: Card, target: Creature) =
        log(uuidGenerator, "Invoked ${card.name} on creature ${target.name}")

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

fun Game.logDamageOnCreature(uuidGenerator: UUIDGenerator, card: Card, targetCreature: Creature, amount: Int) =
        log(uuidGenerator, "${card.name} dealt $amount damage to ${targetCreature.name}")
