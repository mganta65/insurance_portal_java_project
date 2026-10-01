package com.example.insuranceportal.repository;

import com.example.insuranceportal.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
