package com.github.gzaro.card_cost.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.gzaro.card_cost.clearing_cost.core.Country;

public class ClearingCostAcceptanceTest extends AbstractAcceptanceTest {

    @Test
    public void whenGetClearingCostThenFullCountryListIsReturned() {
        ResponseEntity<Country[]> response = getClearingCost();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(249);
    }

    @Test
    public void whenGetClearingCostOfCountryThenCorrectCountryIsReturned() {
        ResponseEntity<Country> response = getClearingCost("GR");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().alpha2()).isEqualTo("GR");
        assertThat(response.getBody().alpha3()).isEqualTo("GRC");
        assertThat(response.getBody().numeric()).isEqualTo("300");
    }

    @Test
    public void whenGetClearingCostOfInvalidCountryCodeThen400IsReturned() {
        ResponseEntity<Country> response = getClearingCost("gr");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenGetClearingCostOfUnknownCountryThen404IsReturned() {
        ResponseEntity<Country> response = getClearingCost("XX");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void givenUpdatedCountryCostWhenGetCountryCostThenUpdatedValueIsReturned() {
        updateClearingCost("GR", 178.115);
        ResponseEntity<Country> response = getClearingCost("GR");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().clearingCostUsd()).isEqualTo(BigDecimal.valueOf(178.12));
    }
}
