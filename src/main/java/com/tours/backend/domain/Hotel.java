package com.tours.backend.domain;

import java.util.Set;

import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String description;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToMany
    @JoinTable(name = "hotel_amenity",
               joinColumns = @JoinColumn(name = "hotel_id"),
               inverseJoinColumns = @JoinColumn(name = "amenity_id")
               )
    private Set<Amenity> amenities;

    @ManyToMany
    @JoinTable(name = "hotel_trip",
               joinColumns = @JoinColumn(name = "hotel_id"),
               inverseJoinColumns = @JoinColumn(name = "trip_id")
               )
    private Set<Trip> trips;
    
}
