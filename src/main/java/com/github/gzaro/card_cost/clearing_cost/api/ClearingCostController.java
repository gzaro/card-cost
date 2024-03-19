package com.github.gzaro.card_cost.clearing_cost.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gzaro.card_cost.clearing_cost.core.ClearingCostService;
import com.github.gzaro.card_cost.clearing_cost.core.Country;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping(ClearingCostController.CLEARING_COST_PATH)
public class ClearingCostController {

    public static final String CLEARING_COST_PATH = "/clearing-cost";

    private final ClearingCostService clearingCostService;

    public ClearingCostController(ClearingCostService clearingCostService) {
        this.clearingCostService = clearingCostService;
    }

    @GetMapping
    @CrossOrigin(origins = "*")
    public Iterable<Country> getAll() {
        return clearingCostService.getAllCountries();
    }

    @GetMapping("/{alpha2}")
    @CrossOrigin(origins = "*")
    public Country getSingle(
            @PathVariable("alpha2") @Valid @Pattern(regexp = "^[A-Z]{2}$") String alpha2) {
        return clearingCostService.getCountry(alpha2);
    }

    @PutMapping("/{alpha2}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> UpdateClearingCost(
            @PathVariable("alpha2") @Valid @Pattern(regexp = "^[A-Z]{2}$") String alpha2,
            @RequestBody ClearingCostUpdateRequest request) {
        clearingCostService.updateClearingCost(alpha2, request.clearingCost());
        return ResponseEntity.noContent().<Void>build();
    }

}
