package com.tours.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tours.backend.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {    
}
