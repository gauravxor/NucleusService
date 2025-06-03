package com.clumsycoder.nucleusservice.service;

import com.clumsycoder.controlshift.commons.exceptions.DatabaseException;
import com.clumsycoder.controlshift.commons.exceptions.DuplicateResourceException;
import com.clumsycoder.controlshift.commons.exceptions.ResourceNotFoundException;
import com.clumsycoder.nucleusservice.dto.request.CreatePlayerRequest;
import com.clumsycoder.nucleusservice.dto.request.PlayerUpdateRequest;
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
    private final PasswordEncoder passwordEncoder;

    public Optional<Player> getPlayerById(String playerId) {
        return playerRepository.findById(playerId);
    }

    public Optional<Player> getPlayerByEmail(String email) {
        return playerRepository.findByEmail(email);
    }

    public Player createPlayer(CreatePlayerRequest request) {
        try {
            Player player = new Player();
            player.setEmail(request.getEmail());
            player.setPassword(passwordEncoder.encode(request.getPassword()));
            return playerRepository.save(player);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateResourceException("Player already exist");
        }
    }

    public Player updatePlayer(String playerId, PlayerUpdateRequest request) {
        Optional<Player> playerOpt = playerRepository.findById(playerId);
        if (playerOpt.isEmpty()) {
            throw new ResourceNotFoundException("Player does not exist.");
        }

        Player player = playerOpt.get();

        if (request.getEmail() != null)
            player.setEmail(request.getEmail());

        player.setEmailVerified(request.getIsEmailVerified());

        try {
            playerRepository.save(player);
        } catch (Exception e) {
            throw new DatabaseException("Error updating the player data");
        }
        return player;
    }
}