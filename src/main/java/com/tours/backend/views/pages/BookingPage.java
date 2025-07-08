package com.tours.backend.views.pages;

import com.tours.backend.services.ReservtionService;
import com.tours.backend.services.TripService;
import com.tours.backend.services.UserService;
import com.tours.backend.views.components.CenteredContainer;
import com.tours.backend.views.forms.BookingForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("book")
public class BookingPage extends VerticalLayout {

    private final ReservtionService reservationService;
    private final TripService tripService;
    private final UserService userService;

    public BookingPage(ReservtionService reservationService, UserService userService, TripService tripService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.tripService = tripService;

        setSizeFull();
        setPadding(true);
        setSpacing(true);
        CenteredContainer centeredContainer = new CenteredContainer();
        BookingForm bookingForm = new BookingForm(tripService, userService, reservationService);
        centeredContainer.add(bookingForm);
        add(centeredContainer);
    }
}
