package de.schramm.royalbash.core.domain.card.summoningcard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.schramm.royalbash.core.domain.game.board.player.field.Summoning;
import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.AttackingTargetEffect;
import de.schramm.royalbash.core.domain.card.summoningcard.attackingtarget.PlainAttackingTargetEffect;
import de.schramm.royalbash.core.domain.card.summoningcard.defendingtarget.DefendingTargetEffect;
import de.schramm.royalbash.core.domain.card.summoningcard.defendingtarget.PlainDefendingTargetEffect;
import de.schramm.royalbash.core.domain.card.effect.GenericEffect;
import de.schramm.royalbash.core.domain.card.effect.PlainGenericEffect;
import de.schramm.royalbash.core.domain.card.summoningcard.effectwithsourcesummoning.GenericEffectWithSourceSummoning;
import de.schramm.royalbash.core.domain.card.summoningcard.effectwithsourcesummoning
        .PlainGenericEffectWithSourceSummoning;
import de.schramm.royalbash.core.domain.game.board.player.field.SummoningCard;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
abstract class AbstractSummoningCard implements SummoningCard {

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
    private final Set<Tag> tags = new HashSet<>();

    @JsonIgnore
    private GenericEffect playEffect = new PlainGenericEffect();

    @JsonIgnore
    private AttackingTargetEffect attackingTargetEffect = new PlainAttackingTargetEffect();

    @JsonIgnore
    private DefendingTargetEffect defendingTargetEffect = new PlainDefendingTargetEffect();

    @JsonIgnore
    private GenericEffect ownerTurnStartEffect = new PlainGenericEffect();

    @JsonIgnore
    private GenericEffectWithSourceSummoning ownerTurnStartEffectWithSource =
            new PlainGenericEffectWithSourceSummoning();

    public final Summoning toInstance(UUID id) {
        return Summoning.fromCard(this, id);
    }

    @Override
    public String getCardType() {
        return "Summoning";
    }

    void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }
}
