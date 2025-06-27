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

import com.tours.backend.controller.exceptions.HotelNotFoundException;
import com.tours.backend.domain.Hotel;
import com.tours.backend.domain.dtos.HotelDto;
import com.tours.backend.mapper.HotelMapper;
import com.tours.backend.services.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelMapper hotelMapper;

    @GetMapping
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotelMapper.mapToHotelDtoList(hotels));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long id) throws HotelNotFoundException {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hotelMapper.mapToHotelDto(hotel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDto> updateHotel(@PathVariable Long id, @RequestBody HotelDto hotelDto)
            throws HotelNotFoundException {
        Hotel updatedHotel = hotelService.updateHotel(id, hotelMapper.mapToHotel(hotelDto));
        return ResponseEntity.ok(hotelMapper.mapToHotelDto(updatedHotel));
    }

    @PostMapping
    public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto) {
        Hotel newHotel = hotelService.createHotel(hotelMapper.mapToHotel(hotelDto));
        return ResponseEntity.ok(hotelMapper.mapToHotelDto(newHotel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) throws HotelNotFoundException {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

}
