package de.schramm.royalbash.persistence.player;

import de.schramm.royalbash.data.PlayerData;
import de.schramm.royalbash.model.player.Player;
import de.schramm.royalbash.persistence.deck.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PlayerRepositoryFake implements PlayerRepository {

    private final DeckRepository deckRepository;

    private Map<UUID, PlayerEntity> playerEntityMap = new HashMap<>();

    @Autowired
    public PlayerRepositoryFake(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @PostConstruct
    private void init() {

        saveAll(PlayerData.getPlayerSet());
    }

    @Override
    public Player find(UUID id) {

        PlayerEntity playerEntity = playerEntityMap.get(id);

        if(playerEntity != null) {

            return Player.builder()
                    .id(playerEntity.getId())
                    .name(playerEntity.getName())
                    .email(playerEntity.getEmail())
                    .passwordHash(playerEntity.getPasswordHash())
                    .deckSet(playerEntity.getDeckSet().stream()
                            .map(deckRepository::find)
                            .collect(Collectors.toSet())
                    ).build();
        } else {

            return null;
        }
    }

    @Override
    public Player findByCredentials(
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
    public Player findByName(String name) {

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
    public Player findByEmail(String email) {

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
    public void save(Player player) {

        playerEntityMap.put(player.getId(), PlayerEntity.toEntity(player));
    }

    @Override
    public void delete(UUID id) {

        Player player = find(id);

        if(player != null) {

            playerEntityMap.remove(player.getId());
        }
    }

    private void saveAll(Set<Player> playerSet) {

        playerSet.forEach(this::save);
    }
}
