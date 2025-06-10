package com.clumsycoder.nucleusservice.repositories;

import com.clumsycoder.nucleusservice.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}