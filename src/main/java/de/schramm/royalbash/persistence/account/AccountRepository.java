package de.schramm.royalbash.persistence.account;

import de.schramm.royalbash.model.Account;
import de.schramm.royalbash.persistence.GenericRepository;

public interface AccountRepository extends GenericRepository<Account> {

    Account findByCredentials(
            String playername,
            String email,
            String passwordHash
    );

    Account findByName(String name);

    Account findByEmail(String email);
}
