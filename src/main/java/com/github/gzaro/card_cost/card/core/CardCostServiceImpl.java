package com.github.gzaro.card_cost.card.core;

import org.springframework.stereotype.Service;

import com.github.gzaro.card_cost.clearing_cost.core.ClearingCostService;
import com.github.gzaro.card_cost.clearing_cost.core.Country;

@Service
class CardCostServiceImpl implements CardCostService {

    private final CardCountryService cardCountryService;
    private final ClearingCostService clearingCostService;

    public CardCostServiceImpl(CardCountryService cardCountryService, ClearingCostService clearingCostService) {
        this.cardCountryService = cardCountryService;
        this.clearingCostService = clearingCostService;
    }

    @Override
    public CardCountryCost getCardCountryCost(String cardNumber) {
        String countryCode = cardCountryService.countryCodeOf(cardNumber);
        Country country = clearingCostService.getCountry(countryCode);
        return new CardCountryCost(countryCode, country.clearingCostUsd());
    }

}
