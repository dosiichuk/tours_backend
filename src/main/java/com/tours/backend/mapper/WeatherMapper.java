package com.tours.backend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tours.backend.domain.Weather;
import com.tours.backend.domain.dtos.WeatherApiResponseDto;
import com.tours.backend.domain.dtos.WeatherDto;
import com.tours.backend.repository.LocationRepository;

@Service
public class WeatherMapper {

    @Autowired
    private LocationRepository locationRepository;

    public Weather mapToWeather(WeatherApiResponseDto response) {
        Weather weather = new Weather();
        weather.setLocation(locationRepository.findByName(response.getLocation().getName()));
        weather.setTemperature(response.getCurrent().getTemp_c());
        weather.setHumidity(response.getCurrent().getHumidity());
        return weather;
    }

    public WeatherDto mapToWeatherDto(Weather weather) {
        WeatherDto dto = new WeatherDto();
        dto.setLocation(weather.getLocation().getName());
        dto.setTemperature(weather.getTemperature());
        dto.setHumidity(weather.getHumidity());
        return dto;
    }
    
}
