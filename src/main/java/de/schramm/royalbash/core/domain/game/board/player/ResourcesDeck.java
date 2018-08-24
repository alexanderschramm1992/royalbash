package de.schramm.royalbash.core.domain.game.board.player;

import de.schramm.royalbash.core.domain.game.board.player.field.ResourcesCard;
import lombok.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Builder
@ToString
public class ResourcesDeck {

    private final UUID id;

    @Singular("resourcesCard")
    private final List<ResourcesCard> resourcesCards;

    ResourcesDeck shuffle() {

        val resourceCards = this.resourcesCards;
        Collections.shuffle(resourcesCards);
        return new ResourcesDeck(id, resourcesCards);
    }

    Optional<ResourcesCard> getTopCard() {
        return resourcesCards.isEmpty() ? Optional.empty() : Optional.of(resourcesCards.get(0));
    }

    ResourcesDeck removeTopCard() {

        val resourcesCards = this.resourcesCards;
        resourcesCards.remove(0);
        return new ResourcesDeck(id, resourcesCards);
    }
}
