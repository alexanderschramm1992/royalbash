package de.schramm.royalbash.gameengine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.schramm.royalbash.gameengine.model.card.effect.AttackingTargetEffect;
import de.schramm.royalbash.gameengine.model.card.summoningcard.SummoningCard;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Summoning {

    private final UUID id;
    private final SummoningCard summoningCard;

    private int currentCost;
    private int currentHealth;
    private int currentStrength;

    @JsonIgnore
    @Singular("tag")
    private Set<Tag> tags;

    @JsonIgnore
    private final AttackingTargetEffect attackingTargetEffect;

    public static Summoning fromCard(SummoningCard summoningCard, UUID id) {

        return new Summoning(
            id,
            summoningCard,
            summoningCard.getCostRations(),
            summoningCard.getHealth(),
            summoningCard.getStrength(),
            summoningCard.getTags(),
            summoningCard.getAttackingTargetEffect()
        );
    }

    public void setCurrentHealth(int newHealth) {
        this.currentHealth = newHealth;
    }

    public void reduceCurrentHealth(int amount) {
        currentHealth = currentHealth < amount ? 0 : currentHealth - amount;
    }

    public void increaseCurrentHealth(int amount) {
        currentHealth += amount;
        if(currentHealth > getSummoningCard().getHealth()) {
            currentHealth = getSummoningCard().getHealth();
        }
    }

    public boolean isDead() {
        return currentHealth == 0;
    }
}
