package com.github.gzaro.card_cost.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.gzaro.card_cost.card.core.CardCountryCost;

@AutoConfigureWireMock(port = 0)
public class CardCostAcceptanceTest extends AbstractAcceptanceTest {

    @Test
    public void whenCardNumberIsLessThan8NumericThen400IsReturned() {
        ResponseEntity<CardCountryCost> response = getCardCost("1234567");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenCardNumberIsMoreThan19NumericThen400IsReturned() {
        ResponseEntity<CardCountryCost> response = getCardCost("01234567890123456789");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenCardNumberContainsAlphaThen400IsReturned() {
        ResponseEntity<CardCountryCost> response = getCardCost("1234567a");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenIssuerIsUnknownThen404IsReturned() {
        ResponseEntity<CardCountryCost> response = getCardCost("222222123456");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void whenExternalServiceReturnsEmptyBodyThen404IsReturned() {
        ResponseEntity<CardCountryCost> response = getCardCost("333333123456");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void givenCountryCostWhenGetCostOfCardInCountryThenCorrectCostIsReturned() {
        BigDecimal cost = BigDecimal.valueOf(12.05);
        updateClearingCost("DK", cost.doubleValue());
        ResponseEntity<CardCountryCost> response = getCardCost("999999123456");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().cost()).isEqualTo(cost);
    }
}
