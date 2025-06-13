package com.tours.backend.domain;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Trip {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "trip")
    private Set<Reservation> reservations;

    @ManyToMany(mappedBy = "trips")
    private Set<Hotel> hotels;

    @OneToMany(mappedBy = "trip")
    private Set<Review> reviews;

    public Trip(String title, String description, double price, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    
}
