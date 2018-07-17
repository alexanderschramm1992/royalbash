package de.schramm.royalbash.core.domain.card;

import de.schramm.royalbash.core.domain.card.effect.generic.GenericEffect;
import de.schramm.royalbash.core.domain.card.resourcescard.ResourcesCard;

import java.util.UUID;

public class TestResourcesCard extends ResourcesCard {

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
