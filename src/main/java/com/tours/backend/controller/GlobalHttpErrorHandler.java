package com.tours.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tours.backend.controller.exceptions.HotelNotFoundException;
import com.tours.backend.controller.exceptions.ReservationNotFoundException;
import com.tours.backend.controller.exceptions.TripNotFoundException;
import com.tours.backend.controller.exceptions.UserAlreadyExistsException;
import com.tours.backend.controller.exceptions.UserNotFoundException;
import com.tours.backend.services.AuditService;

@RestControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private AuditService auditService;

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        auditService.auditError("UserNotFoundException", exception.getMessage());
        return new ResponseEntity<>("User with a given ID not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        auditService.auditError("UserAlreadyExistsException", exception.getMessage());
        return new ResponseEntity<>("User with a given email already exists", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TripNotFoundException.class)
    public ResponseEntity<Object> handleTripNotFoundException(TripNotFoundException exception) {
        auditService.auditError("TripNotFoundException", exception.getMessage());
        return new ResponseEntity<>("Trip with a given ID not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Object> handleReservationNotFoundException(ReservationNotFoundException exception) {
        auditService.auditError("ReservationNotFoundException", exception.getMessage());
        return new ResponseEntity<>("Reservation with a given ID not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<Object> handleHotelNotFoundException(HotelNotFoundException exception) {
        auditService.auditError("HotelNotFoundException", exception.getMessage());
        return new ResponseEntity<>("Hotel with a given ID not found", HttpStatus.NOT_FOUND);
    }


}
