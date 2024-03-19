package com.github.gzaro.card_cost.card.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Pattern;

public record CardCostRequest(
        @JsonProperty("card_number")
        @Pattern(regexp = "^[0-9]{8,19}$", message = "card number must be numeric of 8 to 19 length")
        String cardNumber) {
}
