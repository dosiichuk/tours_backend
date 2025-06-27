package com.tours.backend.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tours.backend.domain.Reservation;
import com.tours.backend.domain.ReservationStatus;
import com.tours.backend.domain.dtos.ReservationDto;
import com.tours.backend.repository.TripRepository;
import com.tours.backend.repository.UserRepository;

@Service
public class ReservationMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TripRepository tripRepository;

    public ReservationDto mapToReservationDto(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setUserId(reservation.getUser().getId());
        reservationDto.setTripId(reservation.getTrip().getId());
        reservationDto.setStatus(reservation.getStatus().getStatus());
        reservationDto.setReservationDate(reservation.getReservationDate());
        return reservationDto;
    }

    public Reservation mapToReservation(ReservationDto reservationDto) {
        if (reservationDto == null) {
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setReservationDate(reservationDto.getReservationDate());
        reservation.setStatus(ReservationStatus.fromString(reservationDto.getStatus()));
        reservation.setUser(userRepository.findById(reservationDto.getUserId())
                .orElseThrow(
                        () -> new IllegalArgumentException("User not found with id: " + reservationDto.getUserId())));
        reservation.setTrip(tripRepository.findById(reservationDto.getTripId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Trip not found with id: " + reservationDto.getTripId())));
        return reservation;
    }

    public List<ReservationDto> mapToReservationDtoList(List<Reservation> reservations) {
        if (reservations == null) {
            return null;
        }
        return reservations.stream()
                .map(this::mapToReservationDto)
                .collect(Collectors.toList());
    }

    public List<Reservation> mapToReservationList(List<ReservationDto> reservationDtos) {
        if (reservationDtos == null) {
            return null;
        }
        return reservationDtos.stream()
                .map(this::mapToReservation)
                .collect(Collectors.toList());
    }

}
