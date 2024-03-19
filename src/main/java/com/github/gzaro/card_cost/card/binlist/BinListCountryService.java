package com.github.gzaro.card_cost.card.binlist;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.gzaro.card_cost.card.core.CardCountryService;

import reactor.core.publisher.Mono;

@Service
class BinListCountryService implements CardCountryService {

    private final WebClient webClient;

    public BinListCountryService(@Value("${card-cost.binlist.baseUrl}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Accept-Version", "3")
                .build();
    }

    @Override
    public String countryCodeOf(String cardNumber) {
        String issuerId = cardNumber.substring(0, 6);
        return fetchCountryId(issuerId)
                .orElseThrow(() -> new UnknownIssuerException(issuerId));
    }

    private Optional<String> fetchCountryId(String issuerId) {
        return webClient
                .get()
                .uri("/{issuer}", issuerId)
                .retrieve()
                .onStatus(s -> s.isError(), r -> handleError(issuerId, r))
                .bodyToMono(BinListResponse.class)
                .blockOptional()
                .map(BinListResponse::country)
                .map(BinListCountry::alpha2);
    }

    private Mono<? extends Throwable> handleError(String issuerId, ClientResponse response) {
        switch (response.statusCode()) {
            case HttpStatus.NOT_FOUND:
                return Mono.error(() -> new UnknownIssuerException(issuerId));
            default:
                return Mono.error(() -> new BinListException(response));
        }
    }

}
