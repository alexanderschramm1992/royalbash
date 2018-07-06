package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.model.card.resourcescard.ResourcesCard;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@ToString
public class ResourcesDeck {

    private final UUID id;

    @Singular("resourcesCard")
    private List<ResourcesCard> resourcesCards;

    public void shuffle() {

        Collections.shuffle(resourcesCards);
    }

    ResourcesCard drawCard() {

        return resourcesCards.isEmpty() ? null : resourcesCards.remove(0);
    }
}
