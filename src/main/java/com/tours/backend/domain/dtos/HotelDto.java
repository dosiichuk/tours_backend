package com.tours.backend.domain.dtos;

import java.util.Set;

import lombok.Data;

@Data
public class HotelDto {

    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String description;
    private Long locationId;
    private Set<Long> amenityIds;
    private Set<Long> tripIds;

}
