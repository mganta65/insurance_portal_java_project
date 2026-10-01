package com.example.insuranceportal.controller;

import com.example.insuranceportal.repository.ClaimRepository;
import com.example.insuranceportal.repository.PaymentRepository;
import com.example.insuranceportal.repository.PolicyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/** Web pages (HTML) for Policies, Claims and Payments. The JSON versions are under /api/... */
@Controller
public class PageController {

    private final PolicyRepository policyRepo;
    private final ClaimRepository claimRepo;
    private final PaymentRepository paymentRepo;

    public PageController(PolicyRepository policyRepo, ClaimRepository claimRepo, PaymentRepository paymentRepo) {
        this.policyRepo = policyRepo;
        this.claimRepo = claimRepo;
        this.paymentRepo = paymentRepo;
    }

    @GetMapping("/policies")
    public String policies(Model model) {
        model.addAttribute("policies", policyRepo.findAll());
        return "policies";
    }

    @GetMapping("/claims")
    public String claims(Model model) {
        model.addAttribute("claims", claimRepo.findAll());
        return "claims";
    }

    @GetMapping("/payments")
    public String payments(Model model) {
        model.addAttribute("payments", paymentRepo.findAll());
        return "payments";
    }
}
