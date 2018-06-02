package de.schramm.royalbash.data;

import de.schramm.royalbash.gameengine.model.Account;

import java.util.UUID;

class AccountData {

    static final Account ACCOUNT_1 = Account.builder()
            .id(UUID.fromString("934d46da-041e-4c02-8115-497c04eaaccf"))
            .name("Account Blue")
            .email("player.blue@royalbash.de")
            .passwordHash("123qwe")
            .build();

    static final Account ACCOUNT_2 = Account.builder()
            .id(UUID.fromString("74d41ee9-804a-4840-8c17-8ec4cd2ef16c"))
            .name("Account Red")
            .email("player.red@royalbash.de")
            .passwordHash("qwe123")
            .build();

}
