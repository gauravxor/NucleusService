package com.clumsycoder.nucleusservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerData {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private LocalDate dateOfBirth;
}