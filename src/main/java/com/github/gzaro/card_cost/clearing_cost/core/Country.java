package com.github.gzaro.card_cost.clearing_cost.core;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table("countries")
public class Country {

    @Id
    @Column("alpha2")
    @JsonProperty("alpha2")
    private final String alpha2;

    @Column("alpha3")
    @JsonProperty("alpha3")
    private final String alpha3;

    @Column("numeric")
    @JsonProperty("numeric")
    private final String numeric;

    @Column("name")
    @JsonProperty("name")
    private final String name;

    @Column("clearing_cost_usd")
    @JsonProperty("clearing_cost_usd")
    private BigDecimal clearingCostUsd;

    @Version
    @Column("version")
    @JsonIgnore
    private long version;

    public Country(String alpha2, String alpha3, String numeric, String name, BigDecimal clearingCostUsd) {
        this.alpha2 = alpha2;
        this.alpha3 = alpha3;
        this.numeric = numeric;
        this.name = name;
        this.clearingCostUsd = clearingCostUsd;
    }

    public void increaseVersion() {
        this.version++;
    }

    public String alpha2() {
        return alpha2;
    }

    public String alpha3() {
        return alpha3;
    }

    public String numeric() {
        return numeric;
    }

    public String name() {
        return name;
    }

    public BigDecimal clearingCostUsd() {
        return clearingCostUsd;
    }

    public void clearingCostUsd(BigDecimal value) {
        this.clearingCostUsd = value;
    }

}
