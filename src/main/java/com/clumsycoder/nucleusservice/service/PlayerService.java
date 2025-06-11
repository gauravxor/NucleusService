package com.clumsycoder.nucleusservice.service;

import com.clumsycoder.nucleusservice.dto.request.CreatePlayerRequest;
import com.clumsycoder.nucleusservice.dto.request.UpdatePlayerRequest;
import com.clumsycoder.nucleusservice.exception.EmailAlreadyUsedException;
import com.clumsycoder.nucleusservice.exception.UserNotFoundException;
import com.clumsycoder.nucleusservice.exception.UserOperationException;
import com.clumsycoder.nucleusservice.exception.UsernameAlreadyUsedException;
import com.clumsycoder.nucleusservice.models.Player;
import com.clumsycoder.nucleusservice.repositories.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Player getPlayer(String playerId) {
        return playerRepository
                .findById(playerId)
                .orElseThrow(UserNotFoundException::new);
    }

    public Player createPlayer(CreatePlayerRequest request) {
        if (playerRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyUsedException();
        }

        if (playerRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyUsedException();
        }

        try {
            Player player = new Player();
            player.setFirstName(request.getFirstName());
            player.setLastName(request.getLastName());
            player.setUsername(request.getUsername());
            player.setEmail(request.getEmail());
            player.setDateOfBirth(request.getDateOfBirth());

            return playerRepository.save(player);
        } catch (DataAccessException dae) {
            throw new UserOperationException("Failed to create user");
        }
    }

    public Player updatePlayer(String playerId, UpdatePlayerRequest request) {
        Player player = playerRepository
                .findById(playerId)
                .orElseThrow(UserNotFoundException::new);

        if (request.getUsername() != null) {
            if (request.getUsername().equals(player.getUsername()))
                player.setUsername(request.getUsername());
            else {
                if (playerRepository.existsByUsername(request.getUsername())) {
                    throw new UsernameAlreadyUsedException();
                }
            }
        }

        if (request.getFirstName() != null)
            player.setFirstName(request.getFirstName());

        if (request.getLastName() != null)
            player.setLastName(request.getLastName());

        if (request.getDateOfBirth() != null)
            player.setDateOfBirth(request.getDateOfBirth());

        try {
            playerRepository.save(player);
        } catch (DataAccessException e) {
            throw new UserOperationException("Failed to update user data");
        }
        return player;
    }
}