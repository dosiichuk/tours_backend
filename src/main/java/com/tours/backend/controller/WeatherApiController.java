package com.tours.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tours.backend.domain.Weather;
import com.tours.backend.domain.dtos.WeatherDto;
import com.tours.backend.mapper.WeatherMapper;
import com.tours.backend.weather.WeatherApiFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WeatherApiController {

    private final WeatherApiFacade weatherApiFacade;

    @Autowired
    private final WeatherMapper weatherMapper;

    @GetMapping("/current/{location}")
    public ResponseEntity<WeatherDto> getCurrentWeather(@PathVariable String location) {
        Weather weather = weatherApiFacade.getWeather(location);
        if (weather == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(weatherMapper.mapToWeatherDto(weather));
    }
}
