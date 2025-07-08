package com.tours.backend.views;

import com.tours.backend.domain.dtos.TripDto;
import com.tours.backend.mapper.TripMapper;
import com.tours.backend.services.TripService;
import com.tours.backend.views.components.CenteredContainer;
import com.tours.backend.views.components.Navbar;
import com.tours.backend.views.components.TripCard;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private TripService tripService;
    private TripMapper tripMapper;

    public MainView(TripService tripService, TripMapper tripMapper) {
        this.tripService = tripService;
        this.tripMapper = tripMapper;
        CenteredContainer centeredContainer = new CenteredContainer();
        centeredContainer.add(new Navbar());
        Div cardGrid = new Div();
        CenteredContainer centeredContainerForCardGrid = new CenteredContainer();
        centeredContainerForCardGrid.add(cardGrid);
        cardGrid.getStyle().set("display", "flex");
        cardGrid.getStyle().set("flex-wrap", "wrap");
        cardGrid.getStyle().set("gap", "1em");

        for (TripDto trip : tripMapper.mapToTripDtoList(tripService.getAllTrips())) {
            TripCard card = new TripCard(trip);
            cardGrid.add(card);
        }

        add(centeredContainer, centeredContainerForCardGrid);
    }
    
}
