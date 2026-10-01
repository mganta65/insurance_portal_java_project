package com.example.insuranceportal.controller;

import com.example.insuranceportal.model.Policy;
import com.example.insuranceportal.repository.PolicyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyRestController {

    private static final Logger log = LoggerFactory.getLogger(PolicyRestController.class);
    private final PolicyRepository repo;

    public PolicyRestController(PolicyRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Policy> getAll() {
        log.debug("Fetching all policies");
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Policy> getOne(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Policy create(@RequestBody Policy policy) {
        log.info("Creating policy {}", policy.getPolicyNumber());
        return repo.save(policy);
    }
}
