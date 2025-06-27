package com.tours.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tours.backend.controller.exceptions.TripNotFoundException;
import com.tours.backend.controller.exceptions.UserAlreadyExistsException;
import com.tours.backend.controller.exceptions.UserNotFoundException;

public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("User with a given ID not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return new ResponseEntity<>("User with a given email already exists", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleTripNotFoundException(TripNotFoundException exception) {
        return new ResponseEntity<>("Trip with a given ID not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleReservationNotFoundException(Exception exception) {
        return new ResponseEntity<>("Reservation with a given ID not found", HttpStatus.NOT_FOUND);
    }


}
