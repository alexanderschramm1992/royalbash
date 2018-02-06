package de.schramm.royalbash.persistence.deck;

import de.schramm.royalbash.data.DeckData;
import de.schramm.royalbash.data.PlayerData;
import de.schramm.royalbash.model.deck.Deck;
import de.schramm.royalbash.persistence.card.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DeckRepositoryFake implements DeckRepository{

    private final CardRepository cardRepository;

    private Set<DeckEntity> deckSet = new HashSet<>();

    @PostConstruct
    private void init() {

        DeckData.getDeckSet().forEach(this::save);
    }

    @Autowired
    public DeckRepositoryFake(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Deck find(UUID id) {

        Optional<DeckEntity> deckOptional = deckSet.stream()
                .filter(deck -> deck.getId().equals(id))
                .findFirst();

        if (deckOptional.isPresent()) {

            DeckEntity deckEntity = deckOptional.get();

            return Deck.builder()
                    .id(deckEntity.getId())
                    .cardList(deckEntity.getDeckList().stream()
                            .map(cardRepository::find)
                            .collect(Collectors.toList())
                    ).build();
        } else {

            return null;
        }
    }

    @Override
    public void save(Deck deck) {

        deckSet.add(DeckEntity.toEntity(deck));
    }

    @Override
    public void delete(UUID id) {

        Deck deck = find(id);

        deckSet.remove(DeckEntity.toEntity(deck));
    }
}
