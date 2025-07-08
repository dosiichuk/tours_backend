package com.tours.backend.views.forms;

import com.tours.backend.domain.Reservation;
import com.tours.backend.domain.Trip;
import com.tours.backend.domain.User;
import com.tours.backend.services.ReservtionService;
import com.tours.backend.services.TripService;
import com.tours.backend.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;

public class BookingForm extends FormLayout {
    private final ComboBox<Trip> tripComboBox = new ComboBox<>("Select Trip");
    private final ComboBox<User> userComboBox = new ComboBox<>("Select User");
    private final Div tripDetails = new Div();
    private final Button reserveButton = new Button("Submit Reservation");

    public BookingForm(TripService tripService, UserService userService, ReservtionService reservationService) {
        tripComboBox.setItems(tripService.getAllTrips());
        tripComboBox.setItemLabelGenerator(Trip::getTitle);

        userComboBox.setItems(userService.getAllUsers());
        userComboBox.setItemLabelGenerator(user -> user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");

        tripComboBox.addValueChangeListener(event -> {
            Trip trip = event.getValue();
            tripDetails.removeAll();
            if (trip != null) {
                tripDetails.add(
                    new Paragraph("Description: " + trip.getDescription()),
                    new Paragraph("Price: " + trip.getPrice()),
                    new Paragraph("From: " + trip.getStartDate() + " To: " + trip.getEndDate())
                );
            }
        });

        // Handle reservation submission
        reserveButton.addClickListener(e -> {
            Trip selectedTrip = tripComboBox.getValue();
            User selectedUser = userComboBox.getValue();
            if (selectedTrip == null || selectedUser == null) {
                Notification.show("Please select both a trip and a user.");
                return;
            }
            try {
                Reservation reservation = new Reservation();
                reservation.setTrip(selectedTrip);
                reservation.setUser(selectedUser);
                reservationService.createReservation(reservation);
                Notification.show("Reservation created!");
            } catch (Exception ex) {
                Notification.show("Failed to create reservation: " + ex.getMessage());
            }
        });

        add(tripComboBox, userComboBox, tripDetails, reserveButton);
    }
}
