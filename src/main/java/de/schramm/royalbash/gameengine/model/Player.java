package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.gameengine.exception.GameEngineException;
import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import de.schramm.royalbash.gameengine.model.card.EffectContext;
import de.schramm.royalbash.gameengine.model.card.resourcescard.ResourcesCard;
import de.schramm.royalbash.gameengine.model.card.summoningcard.SummoningCard;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@Builder
@Getter
@ToString
public class Player {

    private final UUID id;
    private final UUID accountId;
    private final SummoningDeck summoningDeck;
    private final ResourcesDeck resourcesDeck;
    private final ResourcePool resourcePool;
    private final Hand hand;
    private final Graveyard graveyard;
    private final Field field;

    private int health;

    public void playSummoningCard(SummoningCard summoningCard, Target target, UUID id) throws GameEngineException {

        resourcePool.payFor(summoningCard);
        hand.removeCard(summoningCard);
        field.summon(Summoning.fromCard(summoningCard, id), target);

    }

    public void playResourcesCard(ResourcesCard resourcesCard, EffectContext context) throws RuleViolationException {

        resourcePool.payFor(resourcesCard);
        hand.removeCard(resourcesCard);
        resourcesCard.getPlayEffect().apply(context);
    }

    void bury(Summoning summoning) {
        field.bury(summoning);
    }

    public void drawSummoningCard() throws RuleViolationException {
        hand.addCard(summoningDeck.drawCard());
    }

    public void drawResourcesCard() throws RuleViolationException {
        hand.addCard(resourcesDeck.drawCard());
    }

    public Target findTarget(UUID attackedTargetId) throws DomainObjectDoesNotExistException {
        return field.getTarget(attackedTargetId);
    }

    public Summoning findSummoning(UUID summoningId) throws DomainObjectDoesNotExistException {
        return field.getTargets().stream()
            .map(Target::getSummoning)
            .filter(Objects::nonNull)
            .filter(summoning -> summoning.getId().equals(summoningId))
            .findFirst()
            .orElseThrow(() -> new DomainObjectDoesNotExistException(
                    String.format("Summoning %s does not exist", summoningId)
            ));
    }
}
