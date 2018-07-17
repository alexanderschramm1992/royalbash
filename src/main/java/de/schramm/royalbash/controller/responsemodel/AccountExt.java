package de.schramm.royalbash.controller.responsemodel;

import de.schramm.royalbash.core.domain.Blueprint;
import de.schramm.royalbash.core.domain.Account;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class AccountExt {

    private UUID id;
    private String name;

    @Singular("blueprint")
    private Set<Blueprint> blueprints;

    private String reason;

    public static AccountExt fromAccount(Account account) {

        return AccountExt.builder()
                .id(account.getId())
                .name(account.getName())
                .blueprints(account.getBlueprints())
                .build();
    }

    public static AccountExt fromError(String reason) {

        return AccountExt.builder()
                .reason(reason)
                .build();
    }
}
