package com.tours.backend.views.components;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;


public class Navbar extends Div {

    public Navbar() {
        addClassName("navbar");
        add(new Anchor("/", "Home"));
        add(new Anchor("/trips", "Trips"));
        add(new Anchor("/about", "About"));
    }
    
}
