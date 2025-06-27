package com.tours.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tours.backend.domain.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByPriceLessThan(Double price);
}
