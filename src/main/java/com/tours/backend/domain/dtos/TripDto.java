package com.tours.backend.domain.dtos;

import java.time.LocalDate;
import java.util.Set;

import lombok.Data;

@Data
public class TripDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;
}
