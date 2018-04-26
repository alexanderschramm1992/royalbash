package de.schramm.royalbash.persistence.deck;

import de.schramm.royalbash.model.Deck;
import de.schramm.royalbash.persistence.card.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DeckRepositoryFake implements DeckRepository {

    private final CardRepository cardRepository;

    private Set<DeckEntity> deckEntities = new HashSet<>();

    @Autowired
    public DeckRepositoryFake(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Deck find(UUID id) {

        return deckEntities.stream()
                .filter(deckEntity -> deckEntity.getId().equals(id))
                .map(deckEntity -> Deck.builder()
                        .id(deckEntity.getId())
                        .cards(deckEntity.getCards().stream()
                                .map(cardRepository::find)
                                .collect(Collectors.toList())
                        ).build()
                )
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Deck deck) {

        delete(deck.getId());
        deckEntities.add(DeckEntity.toEntity(deck));
    }

    @Override
    public void delete(UUID id) {

        Deck deck = find(id);
        deckEntities.remove(DeckEntity.toEntity(deck));
    }
}
