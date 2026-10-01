package com.example.insuranceportal.controller;

import com.example.insuranceportal.model.Claim;
import com.example.insuranceportal.repository.ClaimRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimRestController {

    private static final Logger log = LoggerFactory.getLogger(ClaimRestController.class);
    private final ClaimRepository repo;

    public ClaimRestController(ClaimRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Claim> getAll() {
        log.debug("Fetching all claims");
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Claim> getOne(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Claim create(@RequestBody Claim claim) {
        log.info("Filing claim {}", claim.getClaimNumber());
        return repo.save(claim);
    }
}
