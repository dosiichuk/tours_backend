package com.tours.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tours.backend.domain.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
    
}
