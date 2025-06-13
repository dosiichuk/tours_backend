package com.tours.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Weather {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "weather")
    private Location location;
    private double temperature;
    private double humidity;

    public Weather(Location location, double temperature, double humidity) {
        this.location = location;
        this.temperature = temperature;
        this.humidity = humidity;
    }    
}
