package com.tours.backend.weather;

import org.springframework.stereotype.Component;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Component
@Getter
public class WeatherApiConfig {
    
    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url.current}")
    private String currentWeatherUrl;

    @Value("${weather.api.url.forecast}")
    private String forecastWeatherUrl;
}
