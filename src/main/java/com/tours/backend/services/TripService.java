package com.tours.backend.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tours.backend.controller.exceptions.TripNotFoundException;
import com.tours.backend.domain.Trip;
import com.tours.backend.repository.TripRepository;

@Service
public class TripService {
    
    @Autowired
    private TripRepository tripRepository;

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip getTripById(Long id) throws TripNotFoundException {
        return tripRepository.findById(id)
                .orElseThrow(() -> new TripNotFoundException("Trip not found with id: " + id));
    }

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long id, Trip tripDetails) throws TripNotFoundException {
        Trip trip = getTripById(id);
        trip.setTitle(tripDetails.getTitle());
        trip.setDescription(tripDetails.getDescription());
        trip.setPrice(tripDetails.getPrice());
        trip.setStartDate(tripDetails.getStartDate());
        trip.setEndDate(tripDetails.getEndDate());
        tripRepository.save(trip);
        return trip;
    }

    public void deleteTrip(Long id) throws TripNotFoundException {
        Trip trip = getTripById(id);
        tripRepository.delete(trip);
    }
}
