package com.github.gzaro.card_cost.card.binlist;

import org.springframework.web.reactive.function.client.ClientResponse;

public class BinListException extends RuntimeException {

    BinListException(ClientResponse response) {
        super(response.request().getMethod() + " " + response.request().getURI() + " returned "
                + response.statusCode());
    }

}
