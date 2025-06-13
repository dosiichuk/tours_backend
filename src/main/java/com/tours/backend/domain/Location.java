package com.tours.backend.domain;

import java.util.Set;

import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String address;
    private String latitude;
    private String longitude;

    @OneToOne
    @JoinColumn(name = "weather_id")
    private Weather weather;

    @OneToMany(mappedBy = "location")
    private Set<Hotel> hotels;
    
}
