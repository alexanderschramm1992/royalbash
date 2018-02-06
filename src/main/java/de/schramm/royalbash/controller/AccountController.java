package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.PlayerRequest;
import de.schramm.royalbash.controller.responsemodel.PlayerExt;
import de.schramm.royalbash.model.player.Player;
import de.schramm.royalbash.persistence.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AccountController {

    private final PlayerRepository playerRepository;

    @Autowired
    public AccountController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @RequestMapping(
            value = "account/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<PlayerExt> login(
            @RequestBody PlayerRequest playerRequest
        ) {

        Player player = playerRepository.findByCredentials(
                playerRequest.getName(),
                playerRequest.getEmail(),
                playerRequest.getPasswordHash()
        );

        if(player != null) {

            return ResponseEntity.ok(PlayerExt.fromPlayer(player));
        } else {

            return ResponseEntity.badRequest().body(PlayerExt.builder().build());
        }
    }

    @RequestMapping(
            value = "account/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity register(
            @RequestBody PlayerRequest playerRequest
    ) {

        Player playerByName = playerRepository.findByName(playerRequest.getName());

        Player playerByEmail = playerRepository.findByEmail(playerRequest.getEmail());

        if(playerByName != null || playerByEmail != null) {

            return ResponseEntity.badRequest().build();
        } else {

            Player player = Player.builder()
                    .id(UUID.randomUUID())
                    .name(playerRequest.getName())
                    .email(playerRequest.getEmail())
                    .passwordHash(playerRequest.getPasswordHash())
                    .build();

            playerRepository.save(player);

            return ResponseEntity.ok().build();
        }
    }
}
