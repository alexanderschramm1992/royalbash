package de.schramm.royalbash.gameengine.model.resourcescard;

import de.schramm.royalbash.gameengine.model.Card;
import de.schramm.royalbash.gameengine.model.ResourcePool;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class ResourcesCard implements Card {

    private final UUID id;
    private final String name;
    private final String image;
    private final String type;
    private final String subType;
    private final String text;
    private final String lore;
    private final int costRations;
    private final int costMaterial;
    private final int costBlessing;

    @Override
    public String getCardType() {
        return "ResourcesCard";
    }

    public abstract void apply(ResourcePool resourcePool);
}
