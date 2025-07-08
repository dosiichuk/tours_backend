package com.tours.backend.views.components;

import com.tours.backend.domain.dtos.TripDto;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;

public class TripCard extends Div {

    public TripCard(TripDto trip) {
        addClassName("trip-card");
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
        add(card);
    }
    
}
