package de.schramm.royalbash.data;

import de.schramm.royalbash.model.SummoningCard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SummoningCardData {

    public static final SummoningCard YOUTHFUL_KNIGHT = SummoningCard.builder()
            .id(UUID.fromString("c31a66c7-2f76-4e81-a922-835272833967"))
            .name("Youthful Knight")
            .image("/img/youthful_knight.jpg")
            .type("Creature")
            .subType("Knight")
            .costRations(2)
            .costMaterial(1)
            .costBlessing(0)
            .strength(3)
            .health(2)
            .build();

    public static final SummoningCard VETERAN_KNIGHT = SummoningCard.builder()
            .id(UUID.fromString("5d10c3a2-78e5-4463-85e0-57e279cac82c"))
            .name("Veteran Knight")
            .image("/img/veteran_knight.jpg")
            .type("Creature")
            .subType("Knight")
            .text("")
            .costRations(4)
            .costMaterial(1)
            .costBlessing(0)
            .strength(3)
            .health(4)
            .build();

    public static final SummoningCard EAGER_RECRUIT = SummoningCard.builder()
            .id(UUID.fromString("fafd0e46-f4ee-4406-b540-b049c26d5f77"))
            .name("Eager Recruit")
            .type("Creature")
            .subType("Knight")
            .costRations(2)
            .costMaterial(0)
            .costBlessing(0)
            .strength(1)
            .health(1)
            .build();

    public static Set<SummoningCard> getCardSet() {

        return new HashSet<>(
                Arrays.asList(
                        YOUTHFUL_KNIGHT,
                        VETERAN_KNIGHT,
                        EAGER_RECRUIT
                )
        );
    }

}
