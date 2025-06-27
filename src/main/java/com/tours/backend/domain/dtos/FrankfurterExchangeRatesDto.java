package com.tours.backend.domain.dtos;

import java.util.Map;


import lombok.Data;

@Data
public class FrankfurterExchangeRatesDto {

    private String base;
    private String date;
    private Map<String, Double> rates;
    
}
