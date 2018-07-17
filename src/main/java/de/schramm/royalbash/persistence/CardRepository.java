package de.schramm.royalbash.persistence;

import de.schramm.royalbash.core.domain.card.summoningcard.SummoningCard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CardRepository extends MongoRepository<SummoningCard, UUID> {

}
