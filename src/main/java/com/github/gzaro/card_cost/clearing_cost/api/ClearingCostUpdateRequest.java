package com.github.gzaro.card_cost.clearing_cost.api;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClearingCostUpdateRequest(@JsonProperty("clearing_cost") BigDecimal clearingCost) {

}
