package com.example.soccermanagerapi.repository;

import com.example.soccermanagerapi.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
    boolean existsByNameAndCountry(String name, String country);
}
