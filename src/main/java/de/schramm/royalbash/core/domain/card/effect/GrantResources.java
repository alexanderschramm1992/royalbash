package de.schramm.royalbash.core.domain.card.effect;

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
    public void apply(GameAccessor game, PlayerAccessor owner) {

        owner.setResourcePool(owner.getResourcePool().alterRations(rations));
        owner.setResourcePool(owner.getResourcePool().alterMaterial(material));
        owner.setResourcePool(owner.getResourcePool().alterBlessing(blessing));
    }

    public static GrantResources getInstance(int rations, int material, int blessing) {
        return new GrantResources(rations, material, blessing);
    }
}
