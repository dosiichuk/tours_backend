package com.tours.backend.frankfurter;

import java.net.URI;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tours.backend.domain.dtos.FrankfurterExchangeRatesDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FrankfurterApiClient {

    private final FrankfurterApiConfig frankfurterApiConfig;
    private final RestTemplate restTemplate;

    public FrankfurterExchangeRatesDto getExchangeRate(String baseCurrency) {
        try {
            FrankfurterExchangeRatesDto response = restTemplate.getForObject(
                    buildUriCurrentExchangeRate(baseCurrency), FrankfurterExchangeRatesDto.class);
            return response;
        } catch (RestClientException e) {
            return null;
        }
    }

    public URI buildUriCurrentExchangeRate(String baseCurrency) {
        String uriString = String.format(
                "%s?base=%s",
                frankfurterApiConfig.getApiUrl(),
                baseCurrency);
        return UriComponentsBuilder
                .fromUriString(uriString)
                .build()
                .encode()
                .toUri();
    }
}
