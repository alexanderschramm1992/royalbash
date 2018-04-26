package de.schramm.royalbash.persistence.card;

import de.schramm.royalbash.data.CardData;
import de.schramm.royalbash.model.Card;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CardRepositoryFake implements CardRepository {

    private Map<UUID, Card> cardMap = new HashMap<>();

    @PostConstruct
    private void init() {

        saveAll(CardData.getCardSet());
    }

    @Override
    public Set<UUID> findAllIds() {

        return cardMap.keySet();
    }

    @Override
    public Set<Card> findAll() {

        return cardMap.keySet().stream()
                .map(this::find)
                .collect(Collectors.toSet());
    }

    @Override
    public Card find(UUID id) {

        return cardMap.get(id);
    }

    @Override
    public void saveAll(Set<Card> cardSet) {

        cardSet.forEach(this::save);
    }

    @Override
    public void save(Card card) {

        cardMap.put(card.getId(), card);
    }

    @Override
    public void delete(UUID id) {

        // Cards cannot be deleted
    }
}
