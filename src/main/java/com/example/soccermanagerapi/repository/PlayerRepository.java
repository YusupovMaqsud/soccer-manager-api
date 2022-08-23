package com.example.soccermanagerapi.repository;

import com.example.soccermanagerapi.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
