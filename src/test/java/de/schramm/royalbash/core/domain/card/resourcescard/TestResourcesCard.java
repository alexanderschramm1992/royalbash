package de.schramm.royalbash.core.domain.card.resourcescard;

import de.schramm.royalbash.core.domain.card.effect.GenericEffect;
import de.schramm.royalbash.core.domain.card.effect.PlainGenericEffect;
import de.schramm.royalbash.core.domain.card.resourcescard.AbstractResourcesCard;

import java.util.UUID;

public class TestResourcesCard extends AbstractResourcesCard {

    public TestResourcesCard() {
        super(
                UUID.randomUUID(),
                "",
                "",
                "",
                "",
                "",
                "",
                0,
                0,
                0
        );

        this.setPlayEffect(new PlainGenericEffect());
    }

    public TestResourcesCard(UUID id, GenericEffect effect) {
        super(
                id,
                "",
                "",
                "",
                "",
                "",
                "",
                0,
                0,
                0
        );

        this.setPlayEffect(effect);
    }
}
