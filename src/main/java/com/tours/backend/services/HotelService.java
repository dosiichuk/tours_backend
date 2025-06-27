package com.tours.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tours.backend.controller.exceptions.HotelNotFoundException;
import com.tours.backend.domain.Hotel;
import com.tours.backend.repository.HotelRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) throws HotelNotFoundException {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + id));
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Long id, Hotel hotelDetails) throws HotelNotFoundException {
        Hotel hotel = getHotelById(id);
        hotel.setName(hotelDetails.getName());
        hotel.setAddress(hotelDetails.getAddress());
        hotel.setPhoneNumber(hotelDetails.getPhoneNumber());
        hotel.setEmail(hotelDetails.getEmail());
        hotel.setDescription(hotelDetails.getDescription());
        hotelRepository.save(hotel);
        return hotel;
    }

    public void deleteHotel(Long id) throws HotelNotFoundException {
        Hotel hotel = getHotelById(id);
        hotelRepository.delete(hotel);
    }
    
}
