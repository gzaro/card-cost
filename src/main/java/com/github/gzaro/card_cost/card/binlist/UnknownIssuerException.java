package com.github.gzaro.card_cost.card.binlist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnknownIssuerException extends RuntimeException {

    UnknownIssuerException(String issuerId) {
        super("Unknown issuer id " + issuerId);
    }

}
