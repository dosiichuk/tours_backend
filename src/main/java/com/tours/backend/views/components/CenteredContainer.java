package com.tours.backend.views.components;

import com.vaadin.flow.component.html.Div;

public class CenteredContainer extends Div {

    public CenteredContainer() {
        getStyle()
            .set("display", "flex")
            .set("justify-content", "center")
            .set("width", "100%");
    }
    
}
