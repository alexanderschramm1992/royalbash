package de.schramm.royalbash.persistence.account;

import de.schramm.royalbash.data.PlayerData;
import de.schramm.royalbash.model.player.Account;
import de.schramm.royalbash.persistence.deck.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class AccountRepositoryFake implements AccountRepository {

    private final DeckRepository deckRepository;

    private Map<UUID, AccountEntity> playerEntityMap = new HashMap<>();

    @Autowired
    public AccountRepositoryFake(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @PostConstruct
    private void init() {

        saveAll(PlayerData.getPlayerSet());
    }

    @Override
    public Account find(UUID id) {

        AccountEntity accountEntity = playerEntityMap.get(id);

        if(accountEntity != null) {

            return Account.builder()
                    .id(accountEntity.getId())
                    .name(accountEntity.getName())
                    .email(accountEntity.getEmail())
                    .passwordHash(accountEntity.getPasswordHash())
                    .deckSet(accountEntity.getDeckSet().stream()
                            .map(deckRepository::find)
                            .collect(Collectors.toSet())
                    ).build();
        } else {

            return null;
        }
    }

    @Override
    public Account findByCredentials(
            String name,
            String email,
            String passwordHash
    ) {

        if(name.length() <= 0 && email.length() <= 0) {

            return null;
        }

        Optional<UUID> uuidOptional = playerEntityMap.entrySet().stream()
                .filter(entry -> name.length() <= 0 || entry.getValue().getName().equals(name))
                .filter(entry -> email.length() <= 0 || entry.getValue().getEmail().equals(email))
                .filter(entry -> entry.getValue().getPasswordHash().equals(passwordHash))
                .map(Map.Entry::getKey)
                .findFirst();

        if (uuidOptional.isPresent()) {

            return find(uuidOptional.get());
        } else {

            return null;
        }
    }

    @Override
    public Account findByName(String name) {

        Optional<UUID> uuidOptional = playerEntityMap.entrySet().stream()
                .filter(entry -> entry.getValue().getName().equals(name))
                .map(Map.Entry::getKey)
                .findFirst();

        if (uuidOptional.isPresent()) {

            return find(uuidOptional.get());
        } else {

            return null;
        }
    }

    @Override
    public Account findByEmail(String email) {

        Optional<UUID> uuidOptional = playerEntityMap.entrySet().stream()
                .filter(entry -> entry.getValue().getEmail().equals(email))
                .map(Map.Entry::getKey)
                .findFirst();

        if (uuidOptional.isPresent()) {

            return find(uuidOptional.get());
        } else {

            return null;
        }
    }

    @Override
    public void save(Account account) {

        playerEntityMap.put(account.getId(), AccountEntity.toEntity(account));
    }

    @Override
    public void delete(UUID id) {

        Account account = find(id);

        if(account != null) {

            playerEntityMap.remove(account.getId());
        }
    }

    private void saveAll(Set<Account> accountSet) {

        accountSet.forEach(this::save);
    }
}
