package com.github.gzaro.card_cost.card.binlist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
record BinListResponse(BinListCountry country) {

}
