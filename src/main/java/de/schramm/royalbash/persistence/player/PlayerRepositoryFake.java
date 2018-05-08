package de.schramm.royalbash.persistence.player;

import de.schramm.royalbash.data.PlayerData;
import de.schramm.royalbash.model.Player;
import de.schramm.royalbash.persistence.card.CardRepository;
import de.schramm.royalbash.persistence.account.AccountRepository;
import de.schramm.royalbash.persistence.deck.DeckRepository;
import de.schramm.royalbash.persistence.target.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PlayerRepositoryFake implements PlayerRepository {

    private Map<UUID, PlayerEntity> playerInstanceEntityMap = new HashMap<>();

    @PostConstruct
    private void init() {

        PlayerData.getPlayerSet().forEach(this::save);
    }

    private final AccountRepository accountRepository;
    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;
    private final TargetRepository targetRepository;

    @Autowired
    public PlayerRepositoryFake(
            AccountRepository accountRepository,
            DeckRepository deckRepository,
            CardRepository cardRepository,
            TargetRepository targetRepository
    ) {
        this.accountRepository = accountRepository;
        this.deckRepository = deckRepository;
        this.cardRepository = cardRepository;
        this.targetRepository = targetRepository;
    }

    @Override
    public Player find(UUID id) {

        PlayerEntity playerEntity = playerInstanceEntityMap.get(id);

        if(playerEntity != null) {

            return Player.builder()
                    .id(playerEntity.getId())
                    .account(accountRepository.find(playerEntity.getAccount()))
                    .deck(deckRepository.find(playerEntity.getDeck()))
                    .health(playerEntity.getHealth())
                    .cards(playerEntity.getCards().stream()
                            .map(cardRepository::find)
                            .collect(Collectors.toList())
                    )
                    .targets(playerEntity.getTargets().stream()
                            .map(targetRepository::find)
                            .collect(Collectors.toList())
                    ).build();
        } else {

            return null;
        }
    }

    @Override
    public void save(Player player) {

        playerInstanceEntityMap.put(player.getId(), PlayerEntity.toEntity(player));
    }

    @Override
    public void delete(UUID id) {

        playerInstanceEntityMap.remove(id);
    }
}
