package com.tours.backend.domain.dtos;

import lombok.Data;

@Data
public class WeatherDto {
    
    private String location;
    private double temperature;
    private double humidity;
    
}
