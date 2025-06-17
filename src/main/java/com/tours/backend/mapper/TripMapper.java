package com.tours.backend.mapper;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tours.backend.domain.Trip;
import com.tours.backend.domain.dtos.TripDto;

@Service
public class TripMapper {

    public TripDto mapToTripDto(Trip trip) {
        if (trip == null) {
            return null;
        }
        TripDto tripDto = new TripDto();
        tripDto.setId(trip.getId());
        tripDto.setTitle(trip.getTitle());
        tripDto.setDescription(trip.getDescription());
        tripDto.setStartDate(trip.getStartDate());
        tripDto.setEndDate(trip.getEndDate());
        tripDto.setPrice(trip.getPrice());
        return tripDto;
    }

    public Trip mapToTrip(TripDto tripDto) {
        if (tripDto == null) {
            return null;
        }
        Trip trip = new Trip();
        trip.setId(tripDto.getId());
        trip.setTitle(tripDto.getTitle());
        trip.setDescription(tripDto.getDescription());
        trip.setStartDate(tripDto.getStartDate());
        trip.setEndDate(tripDto.getEndDate());
        trip.setPrice(tripDto.getPrice());
        return trip;
    }

    public List<TripDto> mapToTripDtoList(List<Trip> trips) {
        if (trips == null) {
            return null;
        }
        return trips.stream()
                .map(this::mapToTripDto)
                .collect(Collectors.toList());
    }

    public List<Trip> mapToTripList(List<TripDto> tripDtos) {
        if (tripDtos == null) {
            return null;
        }
        return tripDtos.stream()
                .map(this::mapToTrip)
                .collect(Collectors.toList());
    }

}
