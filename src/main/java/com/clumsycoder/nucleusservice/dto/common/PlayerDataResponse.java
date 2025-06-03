package com.clumsycoder.nucleusservice.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerDataResponse {
    private String id;
    private String email;

    @JsonProperty("isEmailVerified")
    private boolean isVerified;
}