package com.tours.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tours.backend.domain.dtos.FrankfurterExchangeRatesDto;
import com.tours.backend.frankfurter.FrankfurterApiFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/frankfurter")
@RequiredArgsConstructor
public class FrankfurterController {

    private final FrankfurterApiFacade frankfurterApiFacade;

    @GetMapping("/exchange-rate/{baseCurrencySymbol}")
    public ResponseEntity<FrankfurterExchangeRatesDto> getExchangeRate(@PathVariable String baseCurrencySymbol) {
        return ResponseEntity.ok(frankfurterApiFacade.getExchangeRate(baseCurrencySymbol));
    }

}
