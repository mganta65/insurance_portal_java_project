package com.example.insuranceportal.repository;

import com.example.insuranceportal.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
