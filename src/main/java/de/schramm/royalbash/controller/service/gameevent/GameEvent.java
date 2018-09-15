package de.schramm.royalbash.controller.service.gameevent;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.schramm.royalbash.controller.service.core.Game;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = NoOpEvent.class, name = "NO_OP"),
        @JsonSubTypes.Type(value = PlayerAttackedEvent.class, name = "PLAYER_ATTACKED"),
        @JsonSubTypes.Type(value = CreatureAttackedEvent.class, name = "CREATURE_ATTACKED")
})
public interface GameEvent {
    Game invoke(Game game);
}
