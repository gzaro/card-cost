package com.github.gzaro.card_cost.clearing_cost.core;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
class CountryService implements ClearingCostService {

    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Country> getAllCountries() {
        return repository.findAll();
    }

    @Override
    public Country getCountry(String alpha2) {
        return repository.findById(alpha2).orElseThrow(() -> new UnknownCountryException(alpha2));
    }

    @Override
    public void updateClearingCost(String alpha2, BigDecimal clearingCost) {
        Country country = repository.findById(alpha2).orElseThrow(() -> new UnknownCountryException(alpha2));
        country.clearingCostUsd(clearingCost);
        repository.save(country);
    }
}
