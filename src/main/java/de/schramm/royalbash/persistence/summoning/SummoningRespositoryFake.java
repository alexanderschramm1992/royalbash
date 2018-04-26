package de.schramm.royalbash.persistence.summoning;

import de.schramm.royalbash.model.Summoning;
import de.schramm.royalbash.persistence.card.CardRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class SummoningRespositoryFake implements SummoningRepository {

    private final CardRepository cardRepository;

    private Map<UUID, SummoningEntity> summoningEntityMap = new HashMap<>();

    public SummoningRespositoryFake(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Summoning find(UUID id) {

        SummoningEntity summoningEntity = summoningEntityMap.get(id);

        if(summoningEntity != null) {

                return Summoning.builder()
                        .id(summoningEntity.getId())
                        .card(cardRepository.find(summoningEntity.getCard()))
                        .currentCost(summoningEntity.getCurrentCost())
                        .currentHealth(summoningEntity.getCurrentHealth())
                        .currentStrength(summoningEntity.getCurrentStrength())
                        .build();
        } else {

            return null;
        }
    }

    @Override
    public void save(Summoning summoning) {

        summoningEntityMap.put(summoning.getId(), SummoningEntity.toEntity(summoning));
    }

    @Override
    public void delete(UUID id) {

        summoningEntityMap.remove(id);
    }
}
