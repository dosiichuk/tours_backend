package com.tours.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tours.backend.domain.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {    
}
