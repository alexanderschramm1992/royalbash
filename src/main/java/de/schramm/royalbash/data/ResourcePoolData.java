package de.schramm.royalbash.data;

import de.schramm.royalbash.model.ResourcePool;

public class ResourcePoolData {

    public static final ResourcePool RESOURCE_POOL_1 = ResourcePool.builder()
            .rations(4)
            .material(3)
            .blessing(2)
            .build();

    public static final ResourcePool RESOURCE_POOL_2 = ResourcePool.builder()
            .rations(0)
            .material(0)
            .blessing(0)
            .build();
}
