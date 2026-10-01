package com.example.insuranceportal.repository;

import com.example.insuranceportal.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
