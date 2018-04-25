package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.PlayerRequest;
import de.schramm.royalbash.controller.responsemodel.PlayerExt;
import de.schramm.royalbash.model.player.Account;
import de.schramm.royalbash.persistence.account.AccountRepository;
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

    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(
            value = "account/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<PlayerExt> login(
            @RequestBody PlayerRequest playerRequest
        ) {

        Account account = accountRepository.findByCredentials(
                playerRequest.getName(),
                playerRequest.getEmail(),
                playerRequest.getPasswordHash()
        );

        if(account != null) {

            return ResponseEntity.ok(PlayerExt.fromPlayer(account));
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

        Account accountByName = accountRepository.findByName(playerRequest.getName());

        Account accountByEmail = accountRepository.findByEmail(playerRequest.getEmail());

        if(accountByName != null || accountByEmail != null) {

            return ResponseEntity.badRequest().build();
        } else {

            Account account = Account.builder()
                    .id(UUID.randomUUID())
                    .name(playerRequest.getName())
                    .email(playerRequest.getEmail())
                    .passwordHash(playerRequest.getPasswordHash())
                    .build();

            accountRepository.save(account);

            return ResponseEntity.ok().build();
        }
    }
}
