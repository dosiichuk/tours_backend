package com.tours.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tours.backend.controller.exceptions.TripNotFoundException;
import com.tours.backend.domain.Trip;
import com.tours.backend.domain.dtos.TripDto;
import com.tours.backend.mapper.TripMapper;
import com.tours.backend.services.TripService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private TripMapper tripMapper;

    @GetMapping
    public ResponseEntity<List<TripDto>> getAllTrips() {
        List<Trip> trips = tripService.getAllTrips();
        return ResponseEntity.ok(tripMapper.mapToTripDtoList(trips));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDto> getTripById(@PathVariable Long id) throws TripNotFoundException {
        Trip trip = tripService.getTripById(id);
        if (trip == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tripMapper.mapToTripDto(trip));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDto> updateTrip(@PathVariable Long id, @RequestBody TripDto tripDto) throws TripNotFoundException {
        Trip updatedTrip = tripService.updateTrip(id, tripMapper.mapToTrip(tripDto));
        return ResponseEntity.ok(tripMapper.mapToTripDto(updatedTrip));
    }

    @PostMapping
    public ResponseEntity<TripDto> createTrip(@RequestBody TripDto tripDto) {
        Trip newTrip = tripService.createTrip(tripMapper.mapToTrip(tripDto));
        return ResponseEntity.ok(tripMapper.mapToTripDto(newTrip));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) throws TripNotFoundException {
        tripService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }
}
