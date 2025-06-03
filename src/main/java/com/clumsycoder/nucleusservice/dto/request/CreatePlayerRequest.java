package com.clumsycoder.nucleusservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlayerRequest {

    @Email(message = "Email must be a valid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 10, message = "Username must be between 3 and 10 characters")
    private String username;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    private String firstName;

    @Size(min = 2, max = 30, message = "last name must be between 2 and 30 characters")
    private String lastName;

    @Past(message = "Date of birth should be a past date")
    private LocalDate dateOfBirth;
}