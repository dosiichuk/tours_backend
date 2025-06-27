package com.tours.backend.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tours.backend.domain.Amenity;
import com.tours.backend.domain.Hotel;
import com.tours.backend.domain.Trip;
import com.tours.backend.domain.dtos.HotelDto;
import com.tours.backend.repository.LocationRepository;

@Service
public class HotelMapper {

    @Autowired
    private LocationRepository locationRepository;

    public HotelDto mapToHotelDto(Hotel hotel) {
        if (hotel == null)
            return null;
        HotelDto dto = new HotelDto();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setAddress(hotel.getAddress());
        dto.setPhoneNumber(hotel.getPhoneNumber());
        dto.setEmail(hotel.getEmail());
        dto.setDescription(hotel.getDescription());
        dto.setLocationId(hotel.getLocation() != null ? hotel.getLocation().getId() : null);
        dto.setAmenityIds(hotel.getAmenities() != null
                ? hotel.getAmenities().stream().map(Amenity::getId).collect(Collectors.toSet())
                : null);
        dto.setTripIds(hotel.getTrips() != null
                ? hotel.getTrips().stream().map(Trip::getId).collect(Collectors.toSet())
                : null);
        return dto;
    }

    public Hotel mapToHotel(HotelDto dto) {
        if (dto == null)
            return null;
        Hotel hotel = new Hotel();
        hotel.setId(dto.getId());
        hotel.setName(dto.getName());
        hotel.setAddress(dto.getAddress());
        hotel.setPhoneNumber(dto.getPhoneNumber());
        hotel.setEmail(dto.getEmail());
        hotel.setDescription(dto.getDescription());
        hotel.setLocation(locationRepository.findById(dto.getLocationId())
                .orElseThrow(() -> new IllegalArgumentException("Location not found with id: " + dto.getLocationId())));
        return hotel;
    }

    public List<HotelDto> mapToHotelDtoList(List<Hotel> hotels) {
        return hotels.stream()
                .map(this::mapToHotelDto)
                .collect(Collectors.toList());
    }

}
