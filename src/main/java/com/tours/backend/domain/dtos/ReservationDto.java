package com.tours.backend.domain.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReservationDto {
    
    private Long id;
    private LocalDate reservationDate;
    private String status;
    private Long userId;
    private Long tripId;
}
