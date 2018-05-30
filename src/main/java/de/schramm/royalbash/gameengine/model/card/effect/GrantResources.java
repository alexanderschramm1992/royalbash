package de.schramm.royalbash.gameengine.model.card.effect;

import de.schramm.royalbash.gameengine.exception.RuleViolationException;
import de.schramm.royalbash.gameengine.model.card.CardContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GrantResources implements PlayEffect {

    private int rations;
    private int material;
    private int blessing;

    @Override
    public void apply(CardContext context) throws RuleViolationException {

        context.getOwner().getResourcePool().alterRations(rations);
        context.getOwner().getResourcePool().alterMaterial(material);
        context.getOwner().getResourcePool().alterBlessing(blessing);
    }

    public static GrantResources getInstance(int rations, int material, int blessing) {
        return new GrantResources(rations, material, blessing);
    }
}
