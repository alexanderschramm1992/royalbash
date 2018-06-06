package de.schramm.royalbash.gameengine.model.card.resourcescard;

import de.schramm.royalbash.gameengine.model.card.effect.GrantResources;

import java.util.UUID;

public class BlessedAltar extends ResourcesCard {

    public BlessedAltar() {
        super(
                UUID.fromString("fe0303e4-5dc6-46a8-b7a2-20a21522aa79"),
                "Blessed Altar",
                "/img/blessed_altar.png",
                "Resources",
                "",
                "Gain <b>3 Blessings</b>",
                "",
                0,
                0,
                0,
                GrantResources.getInstance(0, 0, 3)
        );
    }
}
