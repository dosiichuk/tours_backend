package com.tours.backend.domain.dtos;

import lombok.Data;

@Data
public class NewUserDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
    
}
