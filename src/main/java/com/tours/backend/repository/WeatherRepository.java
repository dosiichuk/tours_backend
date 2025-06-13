package com.tours.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tours.backend.domain.Weather;

public interface WeatherRepository  extends JpaRepository<Weather, Long> {
    
}
