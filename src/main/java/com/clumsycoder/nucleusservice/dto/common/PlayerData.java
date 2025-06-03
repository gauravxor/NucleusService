package com.clumsycoder.nucleusservice.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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