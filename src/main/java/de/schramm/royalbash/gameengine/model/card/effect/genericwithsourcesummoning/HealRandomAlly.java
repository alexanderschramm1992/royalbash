package de.schramm.royalbash.gameengine.model.card.effect.genericwithsourcesummoning;

import de.schramm.royalbash.gameengine.model.Summoning;
import de.schramm.royalbash.gameengine.model.Target;
import de.schramm.royalbash.gameengine.model.card.EffectContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HealRandomAlly implements GenericEffectWithSourceSummoning {

    private final int healAmount;

    @Override
    public void apply(Summoning source, EffectContext context) {

        val damagedSummonings = context.getOwner().getField().getTargets().stream()
                .map(Target::getSummoning)
                .filter(Objects::nonNull)
                .filter(summoning -> summoning != source)
                .filter(Summoning::isWounded)
                .collect(Collectors.toList());

        if(!damagedSummonings.isEmpty()) {

            val randomSummoning = damagedSummonings.get(new Random().nextInt() % damagedSummonings.size());
            randomSummoning.increaseCurrentHealth(healAmount);
        }
    }

    public static HealRandomAlly getInstance(int healAmount) {
        return new HealRandomAlly(healAmount);
    }
}
