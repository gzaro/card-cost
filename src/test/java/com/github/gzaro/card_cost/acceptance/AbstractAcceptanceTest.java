package com.github.gzaro.card_cost.acceptance;

import static com.github.gzaro.card_cost.card.api.CardCostController.CARDS_COST_PATH;
import static com.github.gzaro.card_cost.clearing_cost.api.ClearingCostController.CLEARING_COST_PATH;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.github.gzaro.card_cost.card.api.CardCostRequest;
import com.github.gzaro.card_cost.card.core.CardCountryCost;
import com.github.gzaro.card_cost.clearing_cost.api.ClearingCostUpdateRequest;
import com.github.gzaro.card_cost.clearing_cost.core.Country;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AbstractAcceptanceTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    protected ResponseEntity<Country[]> getClearingCost() {
        return restTemplate.getForEntity(CLEARING_COST_PATH, Country[].class);
    }

    protected ResponseEntity<Country> getClearingCost(String alpha2) {
        return restTemplate.getForEntity(CLEARING_COST_PATH + "/{Country}", Country.class, alpha2);
    }

    protected void updateClearingCost(String alpha2, double costUsd) {
        restTemplate.put(CLEARING_COST_PATH + "/{Country}",
                new ClearingCostUpdateRequest(BigDecimal.valueOf(costUsd)),
                alpha2);
    }

    protected ResponseEntity<CardCountryCost> getCardCost(String cardNumber) {
        return restTemplate.postForEntity(CARDS_COST_PATH,
                new CardCostRequest(cardNumber),
                CardCountryCost.class);
    }
}
