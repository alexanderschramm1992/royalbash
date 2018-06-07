package de.schramm.royalbash.gameengine.model;

import de.schramm.royalbash.gameengine.model.card.summoningcard.SummoningCard;

import java.util.UUID;

class TestSummoningCard extends SummoningCard {

    TestSummoningCard(UUID id) {
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

    TestSummoningCard(UUID id, int costRations, int costMaterial, int costBlessing) {
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