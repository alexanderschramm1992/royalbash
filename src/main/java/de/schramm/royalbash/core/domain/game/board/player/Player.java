package de.schramm.royalbash.core.domain.game.board.player;

import de.schramm.royalbash.core.domain.card.effect.PlayerAccessor;
import de.schramm.royalbash.core.domain.game.Game;
import de.schramm.royalbash.core.domain.game.board.player.field.*;
import de.schramm.royalbash.core.exception.DomainObjectDoesNotExistException;
import de.schramm.royalbash.core.exception.GameEngineException;
import de.schramm.royalbash.core.exception.RuleViolationException;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@Builder
@Getter
@ToString
public class Player implements PlayerAccessor {

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

    public void playResourcesCard(ResourcesCard resourcesCard, Game game, Player owner) throws RuleViolationException {

        resourcePool.payFor(resourcesCard);
        hand.removeCard(resourcesCard);
        resourcesCard.getPlayEffect().apply(game, owner);
    }

    public void bury(Summoning summoning) throws DomainObjectDoesNotExistException, RuleViolationException {
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

    public void purge() {
        field.getTargets().forEach(Target::purge);
    }
}
