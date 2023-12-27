package com.vivek.commerce.authuser;

import lombok.Data;

@Data
public class RegisterDto {
    private String firstName;
    private String email;
    private String password;
    private String lastName;
}
