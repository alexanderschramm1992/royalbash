package de.schramm.royalbash.persistence.player;

import de.schramm.royalbash.model.player.PlayerInstance;
import de.schramm.royalbash.persistence.card.instance.CardInstanceRepository;
import de.schramm.royalbash.persistence.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PlayerRepositoryFake implements PlayerRepository {

    private Map<UUID, PlayerEntity> playerInstanceEntityMap = new HashMap<>();

    private final AccountRepository accountRepository;

    private final CardInstanceRepository cardInstanceRepository;

    @Autowired
    public PlayerRepositoryFake(
            AccountRepository accountRepository,
            CardInstanceRepository cardInstanceRepository
    ) {
        this.accountRepository = accountRepository;
        this.cardInstanceRepository = cardInstanceRepository;
    }

    @Override
    public PlayerInstance find(UUID id) {

        PlayerEntity playerEntity = playerInstanceEntityMap.get(id);

        if(playerEntity != null) {

            return PlayerInstance.builder()
                    .id(playerEntity.getId())
                    .account(accountRepository.find(playerEntity.getPlayer()))
                    .currentHealth(playerEntity.getCurrentHealth())
                    .handCardInstanceList(playerEntity.getHandCardInstanceList().stream()
                            .map(cardInstanceRepository::find)
                            .collect(Collectors.toList())
                    ).build();
        } else {

            return null;
        }
    }

    @Override
    public void save(PlayerInstance playerInstance) {

        playerInstanceEntityMap.put(playerInstance.getId(), PlayerEntity.toEntity(playerInstance));
    }

    @Override
    public void delete(UUID id) {

        playerInstanceEntityMap.remove(id);
    }
}
