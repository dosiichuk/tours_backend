package com.tours.backend.weather;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tours.backend.domain.Weather;
import com.tours.backend.domain.dtos.WeatherApiResponseDto;
import com.tours.backend.mapper.WeatherMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WeatherApiClient {

    @Autowired
    private WeatherMapper weatherMapper;
    private final RestTemplate restTemplate;
    private final WeatherApiConfig weatherApiConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherApiClient.class);

    public Weather getCurrentWeatherForLocation(String location) {
        LOGGER.info("Fetching current weather for location: {}", location);
        try {
            WeatherApiResponseDto response = restTemplate.getForObject(buildUriCurrentWeather(location),
                    WeatherApiResponseDto.class);
            return Optional.ofNullable(response)
                    .map(weatherMapper::mapToWeather)
                    .orElseThrow(null);
        } catch (RestClientException e) {
            LOGGER.error("Error fetching weather data for location: {}", location, e);
            return null;
        }
    }

    public URI buildUriCurrentWeather(String locationName) {
        String uriString = String.format(
                "%s?key=%s&q=%s",
                weatherApiConfig.getCurrentWeatherUrl(),
                weatherApiConfig.getApiKey(),
                locationName);
        return UriComponentsBuilder
                .fromUriString(uriString)
                .build()
                .encode()
                .toUri();
    }

}
