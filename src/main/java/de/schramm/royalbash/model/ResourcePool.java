package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResourcePool {

    private int rations;
    private int material;
    private int blessing;
}
