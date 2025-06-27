package com.tours.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tours.backend.controller.exceptions.ReservationNotFoundException;
import com.tours.backend.domain.Reservation;
import com.tours.backend.repository.ReservationRepository;

@Service
public class ReservtionService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) throws ReservationNotFoundException {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: " + id));
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservationDetails) throws ReservationNotFoundException {
        Reservation reservation = getReservationById(id);
        reservation.setUser(reservationDetails.getUser());
        reservation.setTrip(reservationDetails.getTrip());
        reservation.setStatus(reservationDetails.getStatus());
        reservation.setReservationDate(reservationDetails.getReservationDate());
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) throws ReservationNotFoundException {
        Reservation reservation = getReservationById(id);
        reservationRepository.delete(reservation);
    }
    
}
