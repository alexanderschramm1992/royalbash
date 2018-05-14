package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.AccountRequest;
import de.schramm.royalbash.controller.responsemodel.AccountExt;
import de.schramm.royalbash.model.Account;
import lombok.val;
import de.schramm.royalbash.persistence.account.AccountRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountEntityControllerTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountController accountController;

    private final String playerName = "testName";
    private final String playerEmail = "text@mail.com";
    private final String playerPasswordHash = "123qwe";

    private final Account account = Account.builder()
            .id(UUID.fromString("e6db794c-d2b7-47e0-86cb-c4a13aeeea6f"))
            .name(playerName)
            .email(playerEmail)
            .passwordHash(playerPasswordHash)
            .build();

    @Before
    public void initializeRepository() {
        accountRepository.deleteAll();
        accountRepository.save(account);
    }

    @Test
    public void login_existing_Player_name_and_email() {

        // When

        ResponseEntity<AccountExt> playerExtResponseEntity = accountController.login(
                AccountRequest.builder()
                        .name(playerName)
                        .email(playerEmail)
                        .passwordHash(playerPasswordHash)
                        .build()
        );

        // Then

        Assert.assertThat(playerExtResponseEntity.getBody(), is(AccountExt.fromAccount(account)));
    }

    @Test
    public void login_existing_Player_missing_email() {

        // When

        ResponseEntity<AccountExt> playerExtResponseEntity = accountController.login(
                AccountRequest.builder()
                        .name(playerName)
                        .passwordHash(playerPasswordHash)
                        .build()
        );

        // Then

        Assert.assertThat(playerExtResponseEntity.getBody(), is(AccountExt.fromAccount(account)));
    }

    @Test
    public void login_existing_Player_missing_name() {

        // When

        ResponseEntity<AccountExt> playerExtResponseEntity = accountController.login(
                AccountRequest.builder()
                        .email(playerEmail)
                        .passwordHash(playerPasswordHash)
                        .build()
        );

        // Then

        Assert.assertThat(playerExtResponseEntity.getBody(), is(AccountExt.fromAccount(account)));
    }

    @Test
    public void login_existing_Player_wrong_password() {

        // When

        ResponseEntity<AccountExt> playerExtResponseEntity = accountController.login(
                AccountRequest.builder()
                        .name(playerName)
                        .email(playerEmail)
                        .passwordHash("qwe123")
                        .build()
        );

        // Then

        Assert.assertThat(
                playerExtResponseEntity,
                is(ResponseEntity.badRequest().body(AccountExt.builder().reason("Wrong password").build()))
        );
    }

    @Test
    public void login_non_existing_Player() {

        // When

        ResponseEntity<AccountExt> playerExtResponseEntity = accountController.login(
                AccountRequest.builder()
                        .name("otherName")
                        .email("otherEmail")
                        .passwordHash("qwe123")
                        .build()
        );

        // Then

        Assert.assertThat(
                playerExtResponseEntity,
                is(ResponseEntity.badRequest().body(AccountExt.builder().reason("Account cannot be found").build()))
        );
    }

    @Test
    public void register_non_existing_Player() {

        // Given

        accountRepository.delete(account.getId());

        // When

        val responseEntity = accountController.register(
                AccountRequest.builder()
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

        val responseEntity = accountController.register(
                AccountRequest.builder()
                        .name(playerName)
                        .email(playerEmail)
                        .passwordHash(playerPasswordHash)
                        .build()
        );

        // Then

        Assert.assertThat(responseEntity, is(ResponseEntity.badRequest().build()));
    }

    @Test
    public void testDatabase() {

        System.out.println(accountRepository.findByName(playerName));
    }
}
