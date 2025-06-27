package com.tours.backend.domain.dtos;


import lombok.Data;

@Data
public class WeatherApiResponseDto {

    private Location location;
    private Current current;

    @Data
    public static class Location {
        private String name;
    }

    @Data
    public static class Current {
        private double temp_c;
        private double humidity;
    }
    
}
