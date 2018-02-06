package de.schramm.royalbash.persistence.player;

import de.schramm.royalbash.model.deck.Deck;
import de.schramm.royalbash.model.player.Player;
import lombok.Builder;
import lombok.Value;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
@Builder
public class PlayerEntity {

    private UUID id;
    private String name;
    private String email;
    private String passwordHash;
    private Set<UUID> deckSet;

    public static PlayerEntity toEntity(Player player) {

        return PlayerEntity.builder()
                .id(player.getId())
                .name(player.getName())
                .email(player.getEmail())
                .passwordHash(player.getPasswordHash())
                .deckSet(player.getDeckSet().stream()
                        .map(Deck::getId)
                        .collect(Collectors.toSet())
                ).build();
    }
}
