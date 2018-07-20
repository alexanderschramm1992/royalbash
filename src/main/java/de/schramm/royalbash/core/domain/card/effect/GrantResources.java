package de.schramm.royalbash.core.domain.card.effect;

import de.schramm.royalbash.core.exception.RuleViolationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GrantResources implements GenericEffect {

    private int rations;
    private int material;
    private int blessing;

    @Override
    public void apply(GameAccessor game, PlayerAccessor owner) throws RuleViolationException {

        owner.getResourcePool().alterRations(rations);
        owner.getResourcePool().alterMaterial(material);
        owner.getResourcePool().alterBlessing(blessing);
    }

    public static GrantResources getInstance(int rations, int material, int blessing) {
        return new GrantResources(rations, material, blessing);
    }
}
