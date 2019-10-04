package de.schramm.royalbash.infrastructure.controller.gameevent

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import de.schramm.royalbash.application.GameEvent
import de.schramm.royalbash.application.UUIDGenerator
import de.schramm.royalbash.domain.Game

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
        JsonSubTypes.Type(value = CardDrawnEventDTO::class, name = "CARD_DRAWN"),
        JsonSubTypes.Type(value = CardPlayedOnPlayerEventDTO::class, name = "CARD_PLAYED_ON_PLAYER"),
        JsonSubTypes.Type(value = CardPlayedOnSpotEventDTO::class, name = "CARD_PLAYED_ON_SPOT"),
        JsonSubTypes.Type(value = CreatureAttackedEventDTO::class, name = "CREATURE_ATTACKED"),
        JsonSubTypes.Type(value = NoOpEventDTO::class, name = "NO_OP"),
        JsonSubTypes.Type(value = PlayerAttackedEventDTO::class, name = "PLAYER_ATTACKED"),
        JsonSubTypes.Type(value = TurnEndedEventDTO::class, name = "TURN_ENDED"))
interface GameEventDTO: GameEvent {
    override fun invoke(game: Game, uuidGenerator: UUIDGenerator): Game
}
