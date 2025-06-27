package com.tours.backend.frankfurter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class FrankfurterApiConfig {

    @Value("${frankfurter.api.url}")
    private String apiUrl;
    
}
