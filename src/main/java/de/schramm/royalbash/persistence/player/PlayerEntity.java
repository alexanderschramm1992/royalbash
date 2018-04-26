package de.schramm.royalbash.persistence.player;

import de.schramm.royalbash.model.Card;
import de.schramm.royalbash.model.Player;
import de.schramm.royalbash.model.Summoning;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
@Builder
public class PlayerEntity {

    private UUID id;
    private UUID account;
    private UUID deck;
    private int health;

    @Singular("card")
    private List<UUID> cards;

    @Singular("summoning")
    private List<UUID> summonings;

    static PlayerEntity toEntity(Player player) {

        return PlayerEntity.builder()
                .id(player.getId())
                .account(player.getAccount().getId())
                .deck(player.getDeck().getId())
                .health(player.getHealth())
                .cards(player.getCards().stream()
                        .map(Card::getId)
                        .collect(Collectors.toList())
                )
                .summonings(player.getSummonings().stream()
                        .map(Summoning::getId)
                        .collect(Collectors.toList())
                ).build();
    }
}
