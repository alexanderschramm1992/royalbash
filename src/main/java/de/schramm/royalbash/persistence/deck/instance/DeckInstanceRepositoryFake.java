package de.schramm.royalbash.persistence.deck.instance;

import de.schramm.royalbash.model.deck.DeckInstance;
import de.schramm.royalbash.persistence.card.instance.CardInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DeckInstanceRepositoryFake implements DeckInstanceRepository {

    private final CardInstanceRepository cardInstanceRepository;

    private Map<UUID, DeckInstanceEntity> deckInstanceEntityMap = new HashMap<>();

    @Autowired
    public DeckInstanceRepositoryFake(CardInstanceRepository cardInstanceRepository) {
        this.cardInstanceRepository = cardInstanceRepository;
    }

    @Override
    public DeckInstance find(UUID id) {

        DeckInstanceEntity deckInstanceEntity = deckInstanceEntityMap.get(id);

        if (deckInstanceEntity != null) {

            return DeckInstance.builder()
                    .id(deckInstanceEntity.getId())
                    .cardInstanceList(deckInstanceEntity.getCardInstanceList().stream()
                            .map(cardInstanceRepository::find)
                            .collect(Collectors.toList())
                    ).build();
        } else {

            return null;
        }
    }

    @Override
    public void save(DeckInstance deckInstance) {

        deckInstanceEntityMap.put(deckInstance.getId(), DeckInstanceEntity.toEntity(deckInstance));
    }

    @Override
    public void delete(UUID id) {

        DeckInstance deckInstance = find(id);

        deckInstance.getCardInstanceList().forEach(
                cardInstance -> cardInstanceRepository.delete(cardInstance.getId())
        );

        deckInstanceEntityMap.remove(id);
    }
}
