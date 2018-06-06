package de.schramm.royalbash.gameengine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.schramm.royalbash.gameengine.model.card.effect.AttackSummoningEffect;
import de.schramm.royalbash.gameengine.model.card.summoningcard.SummoningCard;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Set;
import java.util.UUID;

@Builder
@Getter
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
    private final AttackSummoningEffect attackSummoningEffect;

    static Summoning fromCard(SummoningCard summoningCard, UUID id) {

        return Summoning.builder()
                .id(id)
                .summoningCard(summoningCard)
                .currentCost(summoningCard.getCostRations())
                .currentHealth(summoningCard.getHealth())
                .currentStrength(summoningCard.getStrength())
                .build();
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
