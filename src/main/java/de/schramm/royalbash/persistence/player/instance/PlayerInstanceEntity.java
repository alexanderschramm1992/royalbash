package de.schramm.royalbash.persistence.player.instance;

import de.schramm.royalbash.model.card.instance.CardInstance;
import de.schramm.royalbash.model.player.PlayerInstance;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
@Builder
public class PlayerInstanceEntity {

    private UUID id;
    private UUID player;

    private int currentHealth;
    @Singular("handCardInstance")
    private List<UUID> handCardInstanceList;

    static PlayerInstanceEntity toEntity(PlayerInstance playerInstance) {

        return PlayerInstanceEntity.builder()
                .id(playerInstance.getId())
                .player(playerInstance.getPlayer().getId())
                .currentHealth(playerInstance.getCurrentHealth())
                .handCardInstanceList(playerInstance.getHandCardInstanceList().stream()
                        .map(CardInstance::getId)
                        .collect(Collectors.toList())
                ).build();
    }
}
