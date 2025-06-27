package com.tours.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tours.backend.controller.exceptions.ReservationNotFoundException;
import com.tours.backend.domain.dtos.ReservationDto;
import com.tours.backend.mapper.ReservationMapper;
import com.tours.backend.services.ReservtionService;


@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservtionService reservationService;

    @Autowired
    private ReservationMapper reservationMapper;

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        return ResponseEntity.ok(
                reservationMapper.mapToReservationDtoList(reservationService.getAllReservations()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long id)
            throws ReservationNotFoundException {
        return ResponseEntity.ok(reservationMapper.mapToReservationDto(reservationService.getReservationById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id,
            @RequestBody ReservationDto reservationDetails) throws ReservationNotFoundException {
        return ResponseEntity.ok(
                reservationMapper.mapToReservationDto(
                        reservationService.updateReservation(id,
                                reservationMapper.mapToReservation(reservationDetails))));
    }

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
        return ResponseEntity.ok(
                reservationMapper.mapToReservationDto(
                        reservationService.createReservation(reservationMapper.mapToReservation(reservationDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) throws ReservationNotFoundException {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }
}
