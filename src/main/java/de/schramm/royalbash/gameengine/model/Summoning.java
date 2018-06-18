package de.schramm.royalbash.gameengine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import de.schramm.royalbash.gameengine.model.card.EffectContext;
import de.schramm.royalbash.gameengine.model.card.effect.AttackingTargetEffect;
import de.schramm.royalbash.gameengine.model.card.summoningcard.SummoningCard;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Summoning {

    private final UUID id;
    private final SummoningCard summoningCard;

    private int currentHealth;
    private int currentStrength;
    private boolean canAttack;

    @JsonIgnore
    @Singular("tag")
    private Set<Tag> tags;

    @JsonIgnore
    private final AttackingTargetEffect attackingTargetEffect;

    public static Summoning fromCard(SummoningCard summoningCard, UUID id) {

        val summoning = new Summoning(
            id,
            summoningCard,
            summoningCard.getAttackingTargetEffect()
        );

        summoning.currentHealth = summoningCard.getHealth();
        summoning.currentStrength = summoningCard.getStrength();
        summoning.tags = summoningCard.getTags();
        summoning.canAttack = true;

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

    public void dealDamage(Summoning summoning) {
        summoning.currentHealth -= currentStrength;
    }

    public void attackTarget(Target attackedTarget, Player owner, Game game) throws RuleViolationException {

        if(canAttack) {
            attackingTargetEffect.apply(
                    this,
                    attackedTarget,
                    EffectContext.builder()
                            .owner(owner)
                            .game(game)
                            .build()
            );
        } else {
            throw new RuleViolationException(String.format("Summoning %s cannot attack", id));
        }
    }
}
