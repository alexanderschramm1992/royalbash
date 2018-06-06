package de.schramm.royalbash.gameengine.model.card.effect;

import de.schramm.royalbash.gameengine.exception.RuleViolationException;
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
public class HealRandomAlly implements GenericEffectWithSourceSummoning{

    private final int healAmount;

    @Override
    public void apply(Summoning source, EffectContext context) throws RuleViolationException {

        val damagedSummonings = context.getOwner().getField().getTargets().stream()
                .map(Target::getSummoning)
                .filter(Objects::nonNull)
                .filter(summoning -> summoning != source)
                .filter(summoning -> summoning.getCurrentHealth() < summoning.getSummoningCard().getHealth())
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
