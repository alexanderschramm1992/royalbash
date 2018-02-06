package de.schramm.royalbash.data;

import de.schramm.royalbash.model.card.Card;
import de.schramm.royalbash.model.card.Creature;
import de.schramm.royalbash.model.card.Weapon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CardData {

    public static final Creature YOUTHFUL_KNIGHT = Creature.builder()
            .id(UUID.fromString("c31a66c7-2f76-4e81-a922-835272833967"))
            .name("Youthful Knight")
            .cost(2)
            .strength(3)
            .health(2)
            .build();

    public static final Creature VETERAN_KNIGHT = Creature.builder()
            .id(UUID.fromString("5d10c3a2-78e5-4463-85e0-57e279cac82c"))
            .name("Veteran Knight")
            .cost(4)
            .strength(3)
            .health(4)
            .build();

    public static final Creature EAGER_RECRUIT = Creature.builder()
            .id(UUID.fromString("fafd0e46-f4ee-4406-b540-b049c26d5f77"))
            .name("Eager Recruit")
            .cost(1)
            .strength(1)
            .health(1)
            .build();

    public static final Weapon IRON_MACE = Weapon.builder()
            .id(UUID.fromString("66ce3038-3fcc-43ca-ab44-af14d2dcefa8"))
            .name("Iron Mace")
            .cost(2)
            .strength(2)
            .health(0)
            .build();

    public static final Weapon OAKWOOD_SHIELD = Weapon.builder()
            .id(UUID.fromString("c6cb317c-c5ec-427b-b5d5-24d3bd1b4471"))
            .name("Oakwood Shield")
            .cost(2)
            .strength(0)
            .health(2)
            .build();

    public static Set<Card> getCardSet() {

        return new HashSet<>(
                Arrays.asList(
                        YOUTHFUL_KNIGHT,
                        VETERAN_KNIGHT,
                        EAGER_RECRUIT,
                        IRON_MACE,
                        OAKWOOD_SHIELD
                )
        );
    }

}
