package com.clumsycoder.nucleusservice.service;

import com.clumsycoder.controlshift.commons.exceptions.DatabaseException;
import com.clumsycoder.controlshift.commons.exceptions.DuplicateResourceException;
import com.clumsycoder.controlshift.commons.exceptions.ResourceNotFoundException;
import com.clumsycoder.nucleusservice.dto.request.CreatePlayerRequest;
import com.clumsycoder.nucleusservice.dto.request.UpdatePlayerRequest;
import com.clumsycoder.nucleusservice.models.Player;
import com.clumsycoder.nucleusservice.repositories.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Player getPlayer(String playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player does not exist."));
    }

    public Player createPlayer(CreatePlayerRequest request) {
        try {
            Player player = new Player();
            player.setFirstName(request.getFirstName());
            player.setLastName(request.getLastName());
            player.setUsername(request.getUsername());
            player.setEmail(request.getEmail());
            player.setDateOfBirth(request.getDateOfBirth());

            return playerRepository.save(player);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateResourceException("Player already exist");
        }
    }

    public Player updatePlayer(String playerId, UpdatePlayerRequest request) {
        Optional<Player> playerOpt = playerRepository.findById(playerId);
        if (playerOpt.isEmpty()) {
            throw new ResourceNotFoundException("Player does not exist.");
        }

        Player player = playerOpt.get();

        if (request.getFirstName() != null)
            player.setFirstName(request.getFirstName());

        if (request.getLastName() != null)
            player.setLastName(request.getLastName());

        if (request.getUsername() != null)
            player.setUsername(request.getUsername());

        if (request.getDateOfBirth() != null)
            player.setDateOfBirth(request.getDateOfBirth());

        try {
            playerRepository.save(player);
        } catch (Exception e) {
            throw new DatabaseException("Error updating the player data");
        }
        return player;
    }
}