package de.schramm.royalbash.core.domain.game;

import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor
public class Constants {

    private int maxRations = 10;
    private int maxMaterial = 10;
    private int maxBlessing = 10;

    private static Constants singleton = new Constants();

    public static Constants getInstance() { return singleton; }
}
