package com.github.gzaro.card_cost.card.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gzaro.card_cost.card.core.CardCostService;
import com.github.gzaro.card_cost.card.core.CardCountryCost;

import jakarta.validation.Valid;

@RestController
@RequestMapping(CardCostController.CARDS_COST_PATH)
public class CardCostController {

    public static final String CARDS_COST_PATH = "/payment-cards-cost";

    private final CardCostService cardCostService;

    public CardCostController(CardCostService cardCostService) {
        this.cardCostService = cardCostService;
    }

    @PostMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<CardCountryCost> getCardClearingCost(@RequestBody @Valid CardCostRequest request) {
        CardCountryCost cost = cardCostService.getCardCountryCost(request.cardNumber());
        return ResponseEntity.ok().body(cost);
    }

}
