package com.github.gzaro.card_cost.clearing_cost.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnknownCountryException extends RuntimeException {

    UnknownCountryException(String alpha2) {
        super("Unknown country " + alpha2);
    }

}
