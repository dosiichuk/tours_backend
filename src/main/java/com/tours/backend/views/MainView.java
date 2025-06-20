package com.tours.backend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    public MainView() {
        // Create a simple layout with a welcome message
        add(new H1("Welcome to the Tours Application!"));
        add(new Paragraph("This is the main view of the application."));
        add(new Button("Click Me", e -> Notification.show("Button clicked!")));
    }
    
}
