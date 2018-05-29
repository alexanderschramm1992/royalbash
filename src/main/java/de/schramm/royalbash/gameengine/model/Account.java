package de.schramm.royalbash.gameengine.model;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import org.springframework.data.annotation.Id;

import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class Account {

    @Id
    private UUID id;
    private String name;
    private String email;
    private String passwordHash;

    @Singular("blueprint")
    private Set<Blueprint> blueprints;
}
