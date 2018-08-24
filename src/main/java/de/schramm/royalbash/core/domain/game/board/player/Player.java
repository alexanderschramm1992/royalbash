package de.schramm.royalbash.core.domain.game.board.player;

import de.schramm.royalbash.core.domain.card.effect.PlayerAccessor;
import de.schramm.royalbash.core.domain.game.Game;
import de.schramm.royalbash.core.domain.game.board.player.field.*;
import de.schramm.royalbash.core.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.core.exception.GameEngineException;
import de.schramm.royalbash.core.exception.RuleViolationException;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
public class Player implements PlayerAccessor {

    private final UUID id;
    private final UUID accountId;
    private SummoningDeck summoningDeck;
    private ResourcesDeck resourcesDeck;
    private ResourcePool resourcePool;
    private Hand hand;
    private final Graveyard graveyard;
    private Field field;

    private int health;

    public void playSummoningCard(SummoningCard summoningCard, Target target, UUID id) throws GameEngineException {

        if(resourcePool.canSustain(summoningCard)) {
            resourcePool = resourcePool.payFor(summoningCard);
            hand = hand.removeCard(summoningCard);
            field = field.summon(Summoning.fromCard(summoningCard, id), target);
        } else {
            throw new RuleViolationException(String.format("Cannot sustain Card %s", summoningCard.getId()));
        }
    }

    public void playResourcesCard(ResourcesCard resourcesCard, Game game, Player owner) throws RuleViolationException {

        if(resourcePool.canSustain(resourcesCard)) {
            resourcePool = resourcePool.payFor(resourcesCard);
            hand = hand.removeCard(resourcesCard);
            resourcesCard.getPlayEffect().apply(game, owner);
        } else {
            throw new RuleViolationException(String.format("Cannot sustain Card %s", resourcesCard.getId()));
        }
    }

    public void bury(Summoning summoning) throws DomainObjectDoesNotExistException {
        field = field.bury(summoning);
    }

    public void drawSummoningCard() throws RuleViolationException {

        val card = summoningDeck.getTopCard();
        if(card.isPresent()) {
            summoningDeck = summoningDeck.removeTopCard();
            hand = hand.addCard(card.get());
        }
    }

    public void drawResourcesCard() throws RuleViolationException {

        val card = resourcesDeck.getTopCard();
        if(card.isPresent()) {
            resourcesDeck = resourcesDeck.removeTopCard();
            hand = hand.addCard(card.get());
        }
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

    public void purge() {
        field = field.purge();
    }
}
