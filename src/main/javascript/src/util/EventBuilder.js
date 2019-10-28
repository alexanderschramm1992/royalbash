function buildCardPlayedOnSpotEvent(cardInstanceId, ownerId, spotId) {
    return {
        type: "CARD_PLAYED_ON_SPOT",
        cardInstanceId: cardInstanceId,
        ownerId: ownerId,
        targetSpotId: spotId
    }
}

function buildCardPlayedOnPlayerEvent(cardInstanceId, ownerId, playerId) {
    return {
        type: "CARD_PLAYED_ON_PLAYER",
        cardInsatnceId: cardInstanceId,
        ownerId: ownerId,
        targetSpotId: playerId
    }
}

function buildCreatureAttackedEvent(attackerInstanceId, ownerId, defenderInstanceId) {
    return {
        type: "CREATURE_ATTACKED",
        attackerInstanceId: attackerInstanceId,
        ownerId: ownerId,
        defenderInstanceId: defenderInstanceId
    }
}

function buildCardDrawnEvent(playerId) {
    return {
        type: "CARD_DRAWN",
        playerId: playerId,
        amountOfCards: 1
    }
}

export {
    buildCardPlayedOnSpotEvent,
    buildCardPlayedOnPlayerEvent,
    buildCreatureAttackedEvent,
    buildCardDrawnEvent
}
