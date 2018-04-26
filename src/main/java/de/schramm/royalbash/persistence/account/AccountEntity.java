package de.schramm.royalbash.persistence.account;

import de.schramm.royalbash.model.Blueprint;
import de.schramm.royalbash.model.Account;
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
    private Set<UUID> blueprints;

    public static AccountEntity toEntity(Account account) {

        return AccountEntity.builder()
                .id(account.getId())
                .name(account.getName())
                .email(account.getEmail())
                .passwordHash(account.getPasswordHash())
                .blueprints(account.getBlueprints().stream()
                        .map(Blueprint::getId)
                        .collect(Collectors.toSet())
                ).build();
    }
}
