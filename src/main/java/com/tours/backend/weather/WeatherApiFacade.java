package com.tours.backend.weather;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tours.backend.domain.Weather;
import com.tours.backend.services.AuditService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WeatherApiFacade {

    private final WeatherApiClient weatherApiClient;

    @Autowired
    private AuditService auditService;

    public Weather getWeather(String location) {
        auditService.auditWeatherApiCall(location);
        return weatherApiClient.getCurrentWeatherForLocation(location);
    }
    
}
