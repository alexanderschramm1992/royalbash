package de.schramm.royalbash.controller.responsemodel;

import de.schramm.royalbash.gameengine.model.Constants;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StateResponseConstants {

    private Constants constants;
    private String reason;

    public static StateResponseConstants fromConstants(Constants constants) {

        return StateResponseConstants.builder()
                .constants(constants)
                .build();
    }

    public static StateResponseConstants fromError(String reason) {

        return StateResponseConstants.builder()
                .reason(reason)
                .build();
    }
}
