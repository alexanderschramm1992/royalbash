package de.schramm.royalbash.controller;

import de.schramm.royalbash.model.card.Card;
import de.schramm.royalbash.persistence.card.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
public class CardController {

    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {

        this.cardRepository = cardRepository;
    }

    @RequestMapping(
            value = "cards",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Set<Card>> getCardList() {

        Set<Card> cardList = cardRepository.findAll();

        return ResponseEntity.ok(cardList);
    }

    @RequestMapping(
            value = "card",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Card> getCard(
            @RequestParam UUID cardId
    ) {

        Card card = cardRepository.find(cardId);

        if (card != null) {

            return ResponseEntity.ok(card);
        } else {

            return ResponseEntity.badRequest().build();
        }
    }
}
