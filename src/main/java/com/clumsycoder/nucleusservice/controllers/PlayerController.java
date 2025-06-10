package com.clumsycoder.nucleusservice.controllers;

import com.clumsycoder.controlshift.commons.response.ApiResponse;
import com.clumsycoder.controlshift.commons.response.ApiResult;
import com.clumsycoder.nucleusservice.dto.PlayerData;
import com.clumsycoder.nucleusservice.dto.request.CreatePlayerRequest;
import com.clumsycoder.nucleusservice.dto.request.UpdatePlayerRequest;
import com.clumsycoder.nucleusservice.models.Player;
import com.clumsycoder.nucleusservice.service.PlayerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/player")
@AllArgsConstructor
public class PlayerController {
    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);
    private final PlayerService playerService;

    @GetMapping("/{id}")
    public ResponseEntity<PlayerData> getPlayer(@PathVariable String id) {
        Player player = playerService.getPlayer(id);

        PlayerData data = new PlayerData(
                player.getId(),
                player.getEmail(),
                player.getFirstName(),
                player.getLastName(),
                player.getUsername(),
                player.getDateOfBirth()
        );
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlayerData> createPlayer(@Valid @RequestBody CreatePlayerRequest request) {
        Player newPlayer = playerService.createPlayer(request);

        PlayerData playerData = new PlayerData(
                newPlayer.getId(),
                newPlayer.getEmail(),
                newPlayer.getFirstName(),
                newPlayer.getLastName(),
                newPlayer.getUsername(),
                newPlayer.getDateOfBirth()
        );

        return new ResponseEntity<>(playerData, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePlayer(@PathVariable String id, @RequestBody UpdatePlayerRequest request) {

        Player updatedPlayerData = playerService.updatePlayer(id, request);

        PlayerData playerData = new PlayerData(
                updatedPlayerData.getId(),
                updatedPlayerData.getEmail(),
                updatedPlayerData.getFirstName(),
                updatedPlayerData.getLastName(),
                updatedPlayerData.getUsername(),
                updatedPlayerData.getDateOfBirth()
        );

        ApiResult response = new ApiResult()
                .message("Player updated")
                .data(Map.of("player", playerData));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}