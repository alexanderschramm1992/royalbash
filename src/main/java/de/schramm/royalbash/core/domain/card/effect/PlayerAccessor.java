package de.schramm.royalbash.core.domain.card.effect;

import de.schramm.royalbash.core.domain.game.board.player.ResourcePool;

public interface PlayerAccessor {
    ResourcePoolAccessor getResourcePool();
    void setResourcePool(ResourcePool resourcePool);
}
