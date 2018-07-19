package de.schramm.royalbash.core.domain.card.summoningcard;


import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode
public class TestSummoningCard extends AbstractSummoningCard {

    public TestSummoningCard() {
        super(
                UUID.randomUUID(),
                "",
                "",
                "",
                "",
                "",
                "",
                0,
                0,
                0,
                0,
                0
        );
    }

    public TestSummoningCard(UUID id) {
        super(
                id,
                "",
                "",
                "",
                "",
                "",
                "",
                0,
                0,
                0,
                0,
                0
        );
    }

    public TestSummoningCard(UUID id, int costRations, int costMaterial, int costBlessing) {
        super(
                id,
                "",
                "",
                "",
                "",
                "",
                "",
                costRations,
                costMaterial,
                costBlessing,
                0,
                0
        );
    }
}