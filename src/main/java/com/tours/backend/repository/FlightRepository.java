package com.tours.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tours.backend.domain.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    
}
