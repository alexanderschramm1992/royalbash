package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.exception.GameRuleViolationException;
import de.schramm.royalbash.gameengine.model.resourcescard.ResourcesCard;
import de.schramm.royalbash.gameengine.model.summoningcard.SummoningCard;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

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

    public void playSummoningCard(SummoningCard summoningCard, Target target) throws GameRuleViolationException {

        resourcePool.payFor(summoningCard);
        hand.removeCard(summoningCard);
        field.summon(Summoning.fromCard(summoningCard, UUID.randomUUID()), target);
    }

    public void playResourcesCard(ResourcesCard resourcesCard) throws GameRuleViolationException {

        resourcePool.payFor(resourcesCard);
        hand.removeCard(resourcesCard);
        resourcesCard.apply(resourcePool);
    }

    public void bury(Summoning summoning) {

        field.bury(summoning);
    }

    public void drawSummoningCard() throws GameRuleViolationException {

        hand.addCard(summoningDeck.drawCard());
    }

    public void drawResourcesCard() throws GameRuleViolationException {

        hand.addCard(resourcesDeck.drawCard());
    }

    public Target getTarget(UUID targetId) {

        return field.getTarget(targetId);
    }
}
