package de.schramm.royalbash.controller;

import de.schramm.royalbash.controller.responsemodel.StateResponseConstants;
import de.schramm.royalbash.core.domain.game.Constants;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("constants")
public class ConstantsController {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<StateResponseConstants> drawCard() {
        return ResponseEntity.ok(StateResponseConstants.fromConstants(Constants.getInstance()));
    }
}
