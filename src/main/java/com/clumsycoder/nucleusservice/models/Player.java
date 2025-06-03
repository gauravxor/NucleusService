package com.clumsycoder.nucleusservice.models;

import com.clumsycoder.controlshift.commons.generators.Cuid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(
        name = "player",
        schema = "public",
        indexes = {
                @Index(name = "idx_player_id", columnList = "id"),
                @Index(name = "idx_player_email", columnList = "email")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uc_player_email", columnNames = {"email"})
        })
@Getter
@Setter
public class Player {

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isEmailVerified;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        Instant currentTime = Instant.now();
        if (this.id == null) this.id = Cuid.getCuid();
        if (this.createdAt == null) this.createdAt = currentTime;
        if (this.updatedAt == null) this.updatedAt = currentTime;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();

    }
}