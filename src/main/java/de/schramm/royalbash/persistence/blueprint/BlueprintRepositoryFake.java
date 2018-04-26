package de.schramm.royalbash.persistence.blueprint;

import de.schramm.royalbash.data.BlueprintData;
import de.schramm.royalbash.model.Blueprint;
import de.schramm.royalbash.persistence.card.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BlueprintRepositoryFake implements BlueprintRepository {

    private final CardRepository cardRepository;

    private Set<BlueprintEntity> blueprintEntities = new HashSet<>();

    @PostConstruct
    private void init() {

        BlueprintData.getBlueprints().forEach(this::save);
    }

    @Autowired
    public BlueprintRepositoryFake(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Blueprint find(UUID id) {

        return blueprintEntities.stream()
                .filter(blueprintEntity -> blueprintEntity.getId().equals(id))
                .map(blueprintEntity -> Blueprint.builder()
                        .id(blueprintEntity.getId())
                        .cards(blueprintEntity.getCards().stream()
                                .map(cardRepository::find)
                                .collect(Collectors.toList())
                        ).build()
                )
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Blueprint blueprint) {

        blueprintEntities.add(BlueprintEntity.toEntity(blueprint));
    }

    @Override
    public void delete(UUID id) {

        Blueprint blueprint = find(id);

        blueprintEntities.remove(BlueprintEntity.toEntity(blueprint));
    }
}
