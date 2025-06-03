package com.clumsycoder.nucleusservice.dto.request;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePlayerRequest {

    @Size(min = 2, max = 50, message = "First name must be between 2 - 50 characters")
    private String firstName;

    @Size(min = 2, max = 50, message = "Last must be between 2 - 50 characters")
    private String lastName;

    @Size(min = 3, max = 10, message = "Username must be between 3 - 10 characters")
    private String username;

    @Past(message = "Date of birth must be in past")
    private LocalDate dateOfBirth;
}