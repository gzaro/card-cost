package com.github.gzaro.card_cost.clearing_cost.core;

import java.math.BigDecimal;

public interface ClearingCostService {

    Iterable<Country> getAllCountries();

    Country getCountry(String alpha2);

    void updateClearingCost(String alpha2, BigDecimal clearingCost);
}
