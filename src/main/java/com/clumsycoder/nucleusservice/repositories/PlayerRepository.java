package com.clumsycoder.nucleusservice.repositories;

import com.clumsycoder.nucleusservice.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, String> {
    Optional<Player> findByEmail(String emailId);
}