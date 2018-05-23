package de.schramm.royalbash.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ResourcesCard implements Card{

    private UUID id;
    private String name;
    private String image;
    private String type;
    private String subType;
    private String text;
    private String lore;
    private int costRations;
    private int costMaterial;
    private int costBlessing;

    @Override
    public String getCardType() {
        return "ResourcesCard";
    }
}
