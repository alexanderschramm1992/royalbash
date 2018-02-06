package de.schramm.royalbash.controller.requestmodel;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlayerRequest {

    private String name;
    private String email;
    private String passwordHash;

    public static class PlayerRequestBuilder {

        private String name = "";
        private String email = "";
        private String passwordHash = "";
    }
}
