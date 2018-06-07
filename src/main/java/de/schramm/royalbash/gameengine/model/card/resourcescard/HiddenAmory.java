package de.schramm.royalbash.gameengine.model.card.resourcescard;

import de.schramm.royalbash.gameengine.model.card.effect.GrantResources;

import java.util.UUID;

public class HiddenAmory extends ResourcesCard {

    public HiddenAmory() {
        super(
                UUID.fromString("6f6f1704-ec6e-4c93-b17a-d88fef5f55b9"),
                "Hidden Amory",
                "/img/hidden_armory.png",
                "Resources",
                "",
                "Gain <b>3 Material</b>",
                "",
                0,
                0,
                0
        );

        setPlayEffect(GrantResources.getInstance(0, 3, 0));
    }
}
