package de.schramm.royalbash.gameengine.model.card.summoningcard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.schramm.royalbash.gameengine.model.Card;
import de.schramm.royalbash.gameengine.model.Summoning;
import de.schramm.royalbash.gameengine.model.Tag;
import de.schramm.royalbash.gameengine.model.card.effect.AttackSummoningEffect;
import de.schramm.royalbash.gameengine.model.card.effect.PlayEffect;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Singular;

import java.util.Set;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public abstract class SummoningCard implements Card {

    private final UUID id;
    private final String name;
    private final String image;
    private final String type;
    private final String subType;
    private final String text;
    private final String lore;
    private final int costRations;
    private final int costMaterial;
    private final int costBlessing;
    private final int strength;
    private final int health;

    @JsonIgnore
    @Singular("tag")
    private final Set<Tag> tags;

    @JsonIgnore
    private final PlayEffect playEffect;

    @JsonIgnore
    private final AttackSummoningEffect attackSummoningEffect;

    public Summoning toInstance(UUID id) {

        return Summoning.builder()
                .id(id)
                .summoningCard(this)
                .currentCost(costRations)
                .currentStrength(strength)
                .currentHealth(health)
                .tags(tags)
                .build();
    }

    @Override
    public String getCardType() {
        return "SummoningCard";
    }
}
