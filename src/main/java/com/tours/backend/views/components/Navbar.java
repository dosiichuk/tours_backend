package com.tours.backend.views.components;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


public class Navbar extends Div {

    public Navbar() {
        addClassName("navbar");
        HorizontalLayout navLayout = new HorizontalLayout();
        navLayout.setWidthFull();
        navLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        navLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        navLayout.getStyle().set("padding", "0.5rem 2rem");
        navLayout.getStyle().set("background", "#1976d2");
        navLayout.getStyle().set("color", "white");

        Anchor home = new Anchor("", "Home");
        Anchor trips = new Anchor("register", "Register");
        Anchor about = new Anchor("book", "Book trip");

        home.getStyle().set("color", "white").set("text-decoration", "none").set("margin-right", "1.5rem");
        trips.getStyle().set("color", "white").set("text-decoration", "none").set("margin-right", "1.5rem");
        about.getStyle().set("color", "white").set("text-decoration", "none");

        navLayout.add(home, trips, about);
        add(navLayout);
    }
    
}
