package de.schramm.royalbash.core.domain.card.summoningcard;

import de.schramm.royalbash.core.domain.card.effect.attackingtarget.VigorousAttackingTargetEffect;

import java.util.UUID;

public class ProvenCombatant extends SummoningCard {
    public ProvenCombatant() {

        super(
                UUID.fromString("57ee8f87-5548-4ccc-a2a6-0f05bcafb1dd"),
                "Proven Combatant",
                "/img/proven_combatant.png",
                "Creature",
                "Knight",
                "<b>Vigorous</b> <i>(When attacking, Proven Combatant gains +1 Strength)</i>",
                "I am here to end you",
                4,
                1,
                0,
                4,
                3
        );

        addTag(Tag.VIGOROUS);
        setAttackingTargetEffect(new VigorousAttackingTargetEffect());
    }
}
