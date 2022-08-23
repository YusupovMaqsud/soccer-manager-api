package com.example.soccermanagerapi.repository;

import com.example.soccermanagerapi.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransferRepository extends JpaRepository<Transfer,Long> {
    boolean existsByTransferred(boolean transferred);

    Integer findByMarketValue(Integer marketValue);
}
