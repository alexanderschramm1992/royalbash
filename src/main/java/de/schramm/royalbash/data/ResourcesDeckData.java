package de.schramm.royalbash.data;

import de.schramm.royalbash.core.domain.game.board.player.ResourcesDeck;
import de.schramm.royalbash.core.domain.card.resourcescard.BlessedAltar;
import de.schramm.royalbash.core.domain.card.resourcescard.BountifulHarvest;
import de.schramm.royalbash.core.domain.card.resourcescard.HiddenAmory;
import de.schramm.royalbash.core.domain.card.resourcescard.RuralHomestead;

import java.util.UUID;

class ResourcesDeckData {

    static final ResourcesDeck RESOURCES_DECK_1 = ResourcesDeck.builder()
            .id(UUID.fromString("e990e5de-723d-42b4-b799-e4b99070f83b"))
            .resourcesCard(new BountifulHarvest())
            .resourcesCard(new HiddenAmory())
            .resourcesCard(new BlessedAltar())
            .resourcesCard(new RuralHomestead())
            .build();

    static final ResourcesDeck RESOURCES_DECK_2 = ResourcesDeck.builder()
            .id(UUID.fromString("eca301fb-a7f3-4e9c-b538-37bbca79dc57"))
            .resourcesCard(new BountifulHarvest())
            .build();
}
