package com.tours.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tours.backend.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
