package de.schramm.royalbash.data;

import de.schramm.royalbash.model.Account;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AccountData {

    public static final Account ACCOUNT_1 = Account.builder()
            .id(UUID.fromString("934d46da-041e-4c02-8115-497c04eaaccf"))
            .name("Account Blue")
            .email("player.blue@royalbash.de")
            .passwordHash("123qwe")
            .build();

    public static final Account ACCOUNT_2 = Account.builder()
            .id(UUID.fromString("74d41ee9-804a-4840-8c17-8ec4cd2ef16c"))
            .name("Account Red")
            .email("player.red@royalbash.de")
            .passwordHash("qwe123")
            .build();

    public static Set<Account> getPlayerSet() {

        return new HashSet<>(
                Arrays.asList(ACCOUNT_1, ACCOUNT_2)
        );
    }
}
