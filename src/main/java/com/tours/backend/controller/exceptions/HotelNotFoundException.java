package com.tours.backend.controller.exceptions;

public class HotelNotFoundException extends Exception {

    public HotelNotFoundException(String message) {
        super(message);
    }
    
}