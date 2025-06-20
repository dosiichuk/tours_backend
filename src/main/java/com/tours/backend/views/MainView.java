package com.tours.backend.views;

import com.tours.backend.domain.dtos.TripDto;
import com.tours.backend.mapper.TripMapper;
import com.tours.backend.services.TripService;
import com.tours.backend.views.components.Navbar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private TripService tripService;
    private TripMapper tripMapper;

    public MainView(TripService tripService, TripMapper tripMapper) {
        this.tripService = tripService;
        this.tripMapper = tripMapper;
        add(new Navbar());

        Div cardGrid = new Div();
        cardGrid.getStyle().set("display", "flex");
        cardGrid.getStyle().set("flex-wrap", "wrap");
        cardGrid.getStyle().set("gap", "1em");
        System.out.println("Trips found: " + tripService.getAllTrips().size());

        for (TripDto trip : tripMapper.mapToTripDtoList(tripService.getAllTrips())) {
            Div card = new Div();
            card.getStyle().set("border", "1px solid #ccc");
            card.getStyle().set("border-radius", "8px");
            card.getStyle().set("padding", "1em");
            card.getStyle().set("width", "250px");
            card.getStyle().set("box-shadow", "2px 2px 8px #eee");
            card.add(
                new H3(trip.getTitle()),
                new Paragraph(trip.getDescription()),
                new Paragraph("Price: " + trip.getPrice()),
                new Paragraph("From: " + trip.getStartDate() + " To: " + trip.getEndDate())
            );
            cardGrid.add(card);
        }

        add(cardGrid);
    }
    
}
