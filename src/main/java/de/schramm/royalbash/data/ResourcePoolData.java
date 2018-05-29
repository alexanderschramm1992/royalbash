package de.schramm.royalbash.data;

import de.schramm.royalbash.gameengine.model.ResourcePool;

class ResourcePoolData {

    static final ResourcePool RESOURCE_POOL_1 = ResourcePool.builder()
            .rations(4)
            .material(3)
            .blessing(2)
            .build();

    static final ResourcePool RESOURCE_POOL_2 = ResourcePool.builder()
            .rations(0)
            .material(0)
            .blessing(0)
            .build();
}
