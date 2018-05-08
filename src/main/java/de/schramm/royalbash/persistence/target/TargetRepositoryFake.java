package de.schramm.royalbash.persistence.target;

import de.schramm.royalbash.model.Target;
import de.schramm.royalbash.persistence.summoning.SummoningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class TargetRepositoryFake implements TargetRepository {

    private final SummoningRepository summoningRepository;

    private Set<TargetEntity> targetEntities = new HashSet<>();

    @Autowired
    public TargetRepositoryFake(SummoningRepository summoningRepository) {
        this.summoningRepository = summoningRepository;
    }

    @Override
    public Target find(UUID id) {

        return targetEntities.stream()
                .filter(targetEntity -> targetEntity.getId().equals(id))
                .map(
                        targetEntity -> Target.builder()
                            .id(targetEntity.getId())
                            .summoning(summoningRepository.find(targetEntity.getSummoning()))
                            .build()
                )
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Target target) {

        targetEntities.add(TargetEntity.toEntity(target));
    }

    @Override
    public void delete(UUID id) {

        Target target = find(id);
        if (target != null) {

            targetEntities.remove(TargetEntity.toEntity(target));
        }
    }
}
