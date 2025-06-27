package com.tours.backend.frankfurter;

import org.springframework.stereotype.Component;

import com.tours.backend.domain.dtos.FrankfurterExchangeRatesDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FrankfurterApiFacade {

    private final FrankfurterApiClient frankfurterApiClient;
    public FrankfurterExchangeRatesDto getExchangeRate(String baseCurrency) {
        return frankfurterApiClient.getExchangeRate(baseCurrency);
    }
}
