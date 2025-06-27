package com.tours.backend.weather;


import org.springframework.stereotype.Component;

import com.tours.backend.domain.Weather;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WeatherApiFacade {

    private final WeatherApiClient weatherApiClient;

    public Weather getWeather(String location) {
        return weatherApiClient.getCurrentWeatherForLocation(location);
    }
    
}
