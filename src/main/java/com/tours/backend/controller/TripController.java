package com.tours.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tours.backend.domain.Trip;
import com.tours.backend.domain.dtos.TripDto;
import com.tours.backend.mapper.TripMapper;
import com.tours.backend.services.TripService;

@RestController
@RequestMapping("/trips")
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
    
}
