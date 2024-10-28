package com.resident_city_admin.resident_city_admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Name is required")
    private String email;
    private String city;
}
