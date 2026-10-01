package com.example.insuranceportal.controller;

import com.example.insuranceportal.model.Payment;
import com.example.insuranceportal.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {

    private static final Logger log = LoggerFactory.getLogger(PaymentRestController.class);
    private final PaymentRepository repo;

    public PaymentRestController(PaymentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Payment> getAll() {
        log.debug("Fetching all payments");
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getOne(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Payment create(@RequestBody Payment payment) {
        log.info("Recording payment of {}", payment.getAmount());
        return repo.save(payment);
    }
}
