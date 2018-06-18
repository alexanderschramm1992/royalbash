package de.schramm.royalbash.data;

import de.schramm.royalbash.gameengine.model.Summoning;
import de.schramm.royalbash.gameengine.model.card.summoningcard.EagerCadet;
import de.schramm.royalbash.gameengine.model.card.summoningcard.VeteranKnight;

import java.util.UUID;

class SummoningData {

    static final Summoning VETERAN_KNIGHT = Summoning.fromCard(
            new VeteranKnight(),
            UUID.fromString("195913bf-fea9-4906-b504-6a3aea9d27f2")
    );

    static final Summoning EAGER_CADET = Summoning.fromCard(
            new EagerCadet(),
            UUID.fromString("61224c5d-1e70-4205-a9d7-445dc17202dc")
    );
}
