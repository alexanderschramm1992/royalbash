package de.schramm.royalbash.controller.service.gameevent

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import de.schramm.royalbash.controller.service.core.Game

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
        JsonSubTypes.Type(value = CardDrawnEvent::class, name = "CARD_DRAWN"),
        JsonSubTypes.Type(value = CardPlayedOnPlayerEvent::class, name = "CARD_PLAYED_ON_PLAYER"),
        JsonSubTypes.Type(value = CreatureAttackedEvent::class, name = "CREATURE_ATTACKED"),
        JsonSubTypes.Type(value = NoOpEvent::class, name = "NO_OP"),
        JsonSubTypes.Type(value = PlayerAttackedEvent::class, name = "PLAYER_ATTACKED"),
        JsonSubTypes.Type(value = TurnEndedEvent::class, name = "TURN_ENDED"))
interface GameEvent {
    operator fun invoke(game: Game): Game
}
