package de.schramm.royalbash.persistence.account;

import de.schramm.royalbash.model.deck.Deck;
import de.schramm.royalbash.model.player.Account;
import lombok.Builder;
import lombok.Value;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
@Builder
public class AccountEntity {

    private UUID id;
    private String name;
    private String email;
    private String passwordHash;
    private Set<UUID> deckSet;

    public static AccountEntity toEntity(Account account) {

        return AccountEntity.builder()
                .id(account.getId())
                .name(account.getName())
                .email(account.getEmail())
                .passwordHash(account.getPasswordHash())
                .deckSet(account.getDeckSet().stream()
                        .map(Deck::getId)
                        .collect(Collectors.toSet())
                ).build();
    }
}
