package de.schramm.royalbash.persistence.card;

import de.schramm.royalbash.data.SummoningCardData;
import de.schramm.royalbash.model.SummoningCard;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CardRepositoryFake implements CardRepository {

    private Map<UUID, SummoningCard> cardMap = new HashMap<>();

    @PostConstruct
    private void init() {

        saveAll(SummoningCardData.getCardSet());
    }

    @Override
    public Set<UUID> findAllIds() {

        return cardMap.keySet();
    }

    @Override
    public Set<SummoningCard> findAll() {

        return cardMap.keySet().stream()
                .map(this::find)
                .collect(Collectors.toSet());
    }

    @Override
    public SummoningCard find(UUID id) {

        return cardMap.get(id);
    }

    @Override
    public void saveAll(Set<SummoningCard> summoningCardSet) {

        summoningCardSet.forEach(this::save);
    }

    @Override
    public void save(SummoningCard summoningCard) {

        cardMap.put(summoningCard.getId(), summoningCard);
    }

    @Override
    public void delete(UUID id) {

        // Cards cannot be deleted
    }
}
