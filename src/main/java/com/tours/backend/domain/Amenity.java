package com.tours.backend.domain;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.ManyToMany;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Amenity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;


    @ManyToMany(mappedBy = "amenities")
    private Set<Hotel> hotels;

    public Amenity(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
}
