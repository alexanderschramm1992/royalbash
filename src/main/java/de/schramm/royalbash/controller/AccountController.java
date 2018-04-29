package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.requestmodel.AccountRequest;
import de.schramm.royalbash.controller.responsemodel.AccountExt;
import de.schramm.royalbash.model.Account;
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
    public ResponseEntity<AccountExt> login(
            @RequestBody AccountRequest accountRequest
        ) {

        Account account = accountRepository.findByCredentials(
                accountRequest.getName(),
                accountRequest.getEmail(),
                accountRequest.getPasswordHash()
        );

        if(account != null) {

            return ResponseEntity.ok(AccountExt.fromAccount(account));
        } else {

            return ResponseEntity.badRequest().body(AccountExt.builder().build());
        }
    }

    @RequestMapping(
            value = "account/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity register(
            @RequestBody AccountRequest accountRequest
    ) {

        Account accountByName = accountRepository.findByName(accountRequest.getName());

        Account accountByEmail = accountRepository.findByEmail(accountRequest.getEmail());

        if(accountByName != null || accountByEmail != null) {

            return ResponseEntity.badRequest().build();
        } else {

            Account account = Account.builder()
                    .id(UUID.randomUUID())
                    .name(accountRequest.getName())
                    .email(accountRequest.getEmail())
                    .passwordHash(accountRequest.getPasswordHash())
                    .build();

            accountRepository.save(account);

            return ResponseEntity.ok().build();
        }
    }
}
