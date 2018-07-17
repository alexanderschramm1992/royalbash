package de.schramm.royalbash.core.domain.card;

import de.schramm.royalbash.core.domain.card.summoningcard.SummoningCard;

import java.util.UUID;

public class TestSummoningCard extends SummoningCard {

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