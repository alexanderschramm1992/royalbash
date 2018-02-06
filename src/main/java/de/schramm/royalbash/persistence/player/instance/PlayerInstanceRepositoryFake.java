package de.schramm.royalbash.persistence.player.instance;

import de.schramm.royalbash.data.CardData;
import de.schramm.royalbash.data.PlayerData;
import de.schramm.royalbash.model.player.PlayerInstance;
import de.schramm.royalbash.persistence.card.instance.CardInstanceRepository;
import de.schramm.royalbash.persistence.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PlayerInstanceRepositoryFake implements PlayerInstanceRepository {

    private Map<UUID, PlayerInstanceEntity> playerInstanceEntityMap = new HashMap<>();

    private final PlayerRepository playerRepository;

    private final CardInstanceRepository cardInstanceRepository;

    @Autowired
    public PlayerInstanceRepositoryFake(
            PlayerRepository playerRepository,
            CardInstanceRepository cardInstanceRepository
    ) {
        this.playerRepository = playerRepository;
        this.cardInstanceRepository = cardInstanceRepository;
    }

    @Override
    public PlayerInstance find(UUID id) {

        PlayerInstanceEntity playerInstanceEntity = playerInstanceEntityMap.get(id);

        if(playerInstanceEntity != null) {

            return PlayerInstance.builder()
                    .id(playerInstanceEntity.getId())
                    .player(playerRepository.find(playerInstanceEntity.getPlayer()))
                    .currentHealth(playerInstanceEntity.getCurrentHealth())
                    .handCardInstanceList(playerInstanceEntity.getHandCardInstanceList().stream()
                            .map(cardInstanceRepository::find)
                            .collect(Collectors.toList())
                    ).build();
        } else {

            return null;
        }
    }

    @Override
    public void save(PlayerInstance playerInstance) {

        playerInstanceEntityMap.put(playerInstance.getId(), PlayerInstanceEntity.toEntity(playerInstance));
    }

    @Override
    public void delete(UUID id) {

        playerInstanceEntityMap.remove(id);
    }
}
