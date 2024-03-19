package com.github.gzaro.card_cost.card.binlist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
record BinListCountry(String alpha2, String name) {

}
