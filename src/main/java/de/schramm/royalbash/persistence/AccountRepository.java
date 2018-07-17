package de.schramm.royalbash.persistence;

import de.schramm.royalbash.core.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AccountRepository extends MongoRepository<Account, UUID> {

    Account findByName(String name);

    Account findByEmail(String email);
}
