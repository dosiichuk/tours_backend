package com.tours.backend.views.pages;

import com.tours.backend.services.UserService;
import com.tours.backend.views.components.CenteredContainer;
import com.tours.backend.views.forms.RegisterForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("register")
public class RegisterPage extends VerticalLayout{

    private final UserService userService;

    public RegisterPage(UserService userService) {
        this.userService = userService;
        setSizeFull();
        CenteredContainer centeredContainer = new CenteredContainer();

        RegisterForm registerForm = new RegisterForm(userService);
        registerForm.setWidth("400px");
        registerForm.setHeight("400px");
        centeredContainer.add(registerForm);
        add(centeredContainer);

    }
    
}
