package com.tours.backend.views.forms;

import com.tours.backend.domain.Role;
import com.tours.backend.domain.User;
import com.tours.backend.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public class RegisterForm extends FormLayout {

    private final TextField firstName = new TextField("First Name");
    private final TextField lastName = new TextField("Last Name");
    private final EmailField email = new EmailField("Email");
    private final PasswordField password = new PasswordField("Password");
    private final Button registerButton = new Button("Register");

    private final UserService userService;

    public RegisterForm(UserService userService) {
        this.userService = userService;
        add(firstName, lastName, email, password, registerButton);

        registerButton.addClickListener(e -> {
            try {
                User user = new User(
                    email.getValue(),
                    password.getValue(),
                    firstName.getValue(),
                    lastName.getValue(),
                    Role.USER 
                );
                userService.createUser(user);
                Notification.show("Registered: " + firstName.getValue() + " " + lastName.getValue());
                firstName.clear();
                lastName.clear();
                email.clear();
                password.clear();
            } catch (Exception ex) {
                Notification.show("Registration failed: " + ex.getMessage(), 3000, Notification.Position.MIDDLE);
            }
        });
    }
    
}
