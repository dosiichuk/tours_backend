package com.tours.backend.frankfurter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.tours.backend.domain.dtos.FrankfurterExchangeRatesDto;
import com.tours.backend.services.AuditService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FrankfurterApiFacade {

    private final FrankfurterApiClient frankfurterApiClient;

    @Autowired
    private AuditService auditService;

    public FrankfurterExchangeRatesDto getExchangeRate(String baseCurrency) {
        auditService.auditFrankfurterApiCall(baseCurrency);
        return frankfurterApiClient.getExchangeRate(baseCurrency);
    }
}
