package de.schramm.royalbash.persistence.card;

import de.schramm.royalbash.data.CardData;
import de.schramm.royalbash.model.InstanceType;
import de.schramm.royalbash.model.card.Card;
import de.schramm.royalbash.model.card.Creature;
import de.schramm.royalbash.model.card.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CardRepositoryFake implements CardRepository {

    private CreatureRepository creatureRepository;
    private WeaponRepository weaponRepository;

    @Autowired
    public CardRepositoryFake(
            CreatureRepository creatureRepository,
            WeaponRepository weaponRepository
    ) {

        this.creatureRepository = creatureRepository;
        this.weaponRepository = weaponRepository;
    }

    @PostConstruct
    private void init() {

        saveAll(CardData.getCardSet());
    }

    @Override
    public Set<UUID> findAllIds() {

        return Stream.of(
                creatureRepository.findAllIds(),
                weaponRepository.findAllIds()
        )
        .flatMap(Collection::stream)
        .collect(Collectors.toSet());
    }

    @Override
    public Set<Card> findAll() {

        return Stream.of(
                creatureRepository.findAll(),
                weaponRepository.findAll()
        )
        .flatMap(Collection::stream)
        .collect(Collectors.toSet());
    }

    @Override
    public Card find(UUID id) {

        return Stream.of(
                creatureRepository.find(id),
                weaponRepository.find(id)
        )
        .filter(Objects::nonNull)
        .findFirst().orElse(null);
    }

    @Override
    public void saveAll(Set<Card> cardSet) {

        cardSet.stream()
                .filter(card -> card.getInstanceType() == InstanceType.Creature)
                .map(card -> (Creature) card)
                .forEach(creature -> creatureRepository.save(creature));

        cardSet.stream()
                .filter(card -> card.getInstanceType() == InstanceType.Weapon)
                .map(card -> (Weapon) card)
                .forEach(weapon -> weaponRepository.save(weapon));
    }

    @Override
    public void save(Card card) {

        if(card.getInstanceType() == InstanceType.Creature) {

            creatureRepository.save((Creature) card);
        } else if(card.getInstanceType() == InstanceType.Weapon) {

            weaponRepository.save((Weapon) card);
        }
    }

    @Override
    public void delete(UUID id) {

        // Cards cannot be deleted
    }
}
