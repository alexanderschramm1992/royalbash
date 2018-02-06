package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.PlayerRequest;
import de.schramm.royalbash.controller.responsemodel.PlayerExt;
import de.schramm.royalbash.model.player.Player;
import de.schramm.royalbash.persistence.deck.DeckRepository;
import de.schramm.royalbash.persistence.player.PlayerRepository;
import de.schramm.royalbash.persistence.player.PlayerRepositoryFake;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

public class AccountControllerTest {

    private final String playerName = "testName";
    private final String playerEmail = "test@mail.com";
    private final String playerPasswordHash = "123qwe";

    private final Player player = Player.builder()
            .id(UUID.randomUUID())
            .name(playerName)
            .email(playerEmail)
            .passwordHash(playerPasswordHash)
            .build();

    private final DeckRepository deckRepository = mock(DeckRepository.class);

    private final PlayerRepository playerRepository = new PlayerRepositoryFake(deckRepository);

    private final AccountController accountController = new AccountController(playerRepository);

    {
        playerRepository.save(player);
    }

    @Test
    public void login_existing_Player_name_and_email() {

        // When

        ResponseEntity<PlayerExt> playerExtResponseEntity = accountController.login(
                PlayerRequest.builder()
                        .name(playerName)
                        .email(playerEmail)
                        .passwordHash(playerPasswordHash)
                        .build()
        );

        // Then

        Assert.assertThat(playerExtResponseEntity.getBody(), is(PlayerExt.fromPlayer(player)));
    }

    @Test
    public void login_existing_Player_missing_email() {

        // When

        ResponseEntity<PlayerExt> playerExtResponseEntity = accountController.login(
                PlayerRequest.builder()
                        .name(playerName)
                        .passwordHash(playerPasswordHash)
                        .build()
        );

        // Then

        Assert.assertThat(playerExtResponseEntity.getBody(), is(PlayerExt.fromPlayer(player)));
    }

    @Test
    public void login_existing_Player_missing_name() {

        // When

        ResponseEntity<PlayerExt> playerExtResponseEntity = accountController.login(
                PlayerRequest.builder()
                        .email(playerEmail)
                        .passwordHash(playerPasswordHash)
                        .build()
        );

        // Then

        Assert.assertThat(playerExtResponseEntity.getBody(), is(PlayerExt.fromPlayer(player)));
    }

    @Test
    public void login_existing_Player_wrong_password() {

        // When

        ResponseEntity<PlayerExt> playerExtResponseEntity = accountController.login(
                PlayerRequest.builder()
                        .name(playerName)
                        .email(playerEmail)
                        .passwordHash("qwe123")
                        .build()
        );

        // Then

        Assert.assertThat(playerExtResponseEntity, is(ResponseEntity.badRequest().body(PlayerExt.builder().build())));
    }

    @Test
    public void login_non_existing_Player() {

        // When

        ResponseEntity<PlayerExt> playerExtResponseEntity = accountController.login(
                PlayerRequest.builder()
                        .name("otherName")
                        .email("otherEmail")
                        .passwordHash("qwe123")
                        .build()
        );

        // Then

        Assert.assertThat(playerExtResponseEntity, is(ResponseEntity.badRequest().body(PlayerExt.builder().build())));
    }

    @Test
    public void register_non_existing_Player() {

        // Given

        playerRepository.delete(player.getId());

        // When

        ResponseEntity responseEntity = accountController.register(
                PlayerRequest.builder()
                        .name(playerName)
                        .email(playerEmail)
                        .passwordHash(playerPasswordHash)
                        .build()
        );

        // Then

        Assert.assertThat(responseEntity, is(ResponseEntity.ok().build()));
    }

    @Test
    public void register_existing_Player() {

        // When

        ResponseEntity responseEntity = accountController.register(
                PlayerRequest.builder()
                        .name(playerName)
                        .email(playerEmail)
                        .passwordHash(playerPasswordHash)
                        .build()
        );

        // Then

        Assert.assertThat(responseEntity, is(ResponseEntity.badRequest().build()));
    }
}
