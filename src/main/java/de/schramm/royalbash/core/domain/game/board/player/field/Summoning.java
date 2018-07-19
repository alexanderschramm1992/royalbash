package de.schramm.royalbash.core.domain.game.board.player.field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.Tag;
import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.AttackableTarget;
import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.AttackingTargetEffect;
import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.FightableSummoning;
import de.schramm.royalbash.core.domain.card.summoningcard.defendingtarget.DefendingTargetEffect;
import de.schramm.royalbash.core.exception.RuleViolationException;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Summoning implements FightableSummoning {

    private final UUID id;
    private final SummoningCard summoningCard;

    private int currentHealth;
    private int currentStrength;
    private boolean canAttack;
    private boolean canDefend;

    @JsonIgnore
    @Singular("tag")
    private Set<Tag> tags;

    @JsonIgnore
    private final AttackingTargetEffect attackingTargetEffect;

    @JsonIgnore
    private final DefendingTargetEffect defendingTargetEffect;

    public static Summoning fromCard(SummoningCard summoningCard, UUID id) {

        val summoning = new Summoning(
            id,
            summoningCard,
            summoningCard.getAttackingTargetEffect(),
            summoningCard.getDefendingTargetEffect()
        );

        summoning.currentHealth = summoningCard.getHealth();
        summoning.currentStrength = summoningCard.getStrength();
        summoning.tags = summoningCard.getTags();
        summoning.canAttack = true;
        summoning.canDefend = true;

        return summoning;
    }

    public void setCurrentHealth(int newHealth) {
        this.currentHealth = newHealth;
    }

    public void increaseCurrentHealth(int amount) {
        currentHealth += amount;
    }

    public void reduceCurrentHealth(int amount) {
        currentHealth = currentHealth < amount ? 0 : currentHealth - amount;
    }

    public void heal(int amount) {
        currentHealth += amount;
        if(currentHealth > summoningCard.getHealth()) {
            currentHealth = summoningCard.getHealth();
        }
    }

    public void increaseCurrentStrength(int amount) {
        currentStrength += amount;
    }

    public void reduceCurrentStrength(int amount) {
        currentStrength -= amount;
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }

    public boolean isWounded() { return currentHealth < summoningCard.getHealth(); }

    public boolean hasTag(Tag tag) {
        return tags.contains(tag);
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public void setCanDefend(boolean canDefend) { this.canDefend = canDefend; }

    public void dealDamage(FightableSummoning summoning) {

        summoning.setCurrentHealth(summoning.getCurrentHealth() - currentStrength);
    }

    public void attackTarget(
            Target attackedTarget
    ) throws RuleViolationException {

        if(canAttack) {
            attackingTargetEffect.apply(
                    this,
                    attackedTarget
            );
        } else {
            throw new RuleViolationException(String.format("Summoning %s cannot attack", id));
        }
    }

    public void defendTarget(
            FightableSummoning attackingSummoning,
            AttackableTarget attackedTarget
    ) throws RuleViolationException {

        if(canDefend) {
            defendingTargetEffect.apply(
                    attackingSummoning,
                    attackedTarget,
                    this
            );
        } else {
            throw new RuleViolationException(String.format("Summoning %s cannot defend", id));
        }
    }
}
