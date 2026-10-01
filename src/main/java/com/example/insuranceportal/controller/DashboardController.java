package com.example.insuranceportal.controller;

import com.example.insuranceportal.model.Activity;
import com.example.insuranceportal.model.Claim;
import com.example.insuranceportal.model.Payment;
import com.example.insuranceportal.model.Policy;
import com.example.insuranceportal.repository.ClaimRepository;
import com.example.insuranceportal.repository.PaymentRepository;
import com.example.insuranceportal.repository.PolicyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    private static final Logger log = LoggerFactory.getLogger(DashboardController.class);

    private static final String[] TYPES  = {"Health Insurance", "Motor Insurance", "Life Insurance", "Travel Insurance"};
    private static final String[] COLORS = {"#4caf50", "#2196f3", "#ff9800", "#7e57c2"};

    private final PolicyRepository policyRepo;
    private final ClaimRepository claimRepo;
    private final PaymentRepository paymentRepo;

    public DashboardController(PolicyRepository policyRepo, ClaimRepository claimRepo, PaymentRepository paymentRepo) {
        this.policyRepo = policyRepo;
        this.claimRepo = claimRepo;
        this.paymentRepo = paymentRepo;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        log.info("Loading dashboard");

        List<Policy> policies = policyRepo.findAll();
        List<Claim> claims = claimRepo.findAll();
        List<Payment> payments = paymentRepo.findAll();

        long activePolicies = policies.stream().filter(p -> "ACTIVE".equals(p.getStatus())).count();
        long totalPayments = Math.round(payments.stream().mapToDouble(Payment::getAmount).sum());

        // Count policies per type (for the donut chart)
        Map<String, Long> typeCounts = new LinkedHashMap<>();
        for (String type : TYPES) {
            typeCounts.put(type, policies.stream().filter(p -> type.equals(p.getType())).count());
        }

        // Build the CSS gradient for the donut chart
        StringBuilder gradient = new StringBuilder();
        double start = 0;
        for (int i = 0; i < TYPES.length; i++) {
            long count = typeCounts.get(TYPES[i]);
            if (count == 0) continue;
            double end = start + (count * 100.0 / policies.size());
            if (gradient.length() > 0) gradient.append(", ");
            gradient.append(COLORS[i]).append(" ").append(start).append("% ").append(end).append("%");
            start = end;
        }
        if (gradient.length() == 0) gradient.append("#dddddd 0% 100%");

        // Recent activities = payments + claims, newest first
        List<Activity> activities = new ArrayList<>();
        for (Payment p : payments) {
            activities.add(new Activity("Payment Successful", p.getDescription(),
                    "₹" + String.format("%,.0f", p.getAmount()), p.getPaymentDate()));
        }
        for (Claim c : claims) {
            activities.add(new Activity("Claim Submitted", "Claim ID: " + c.getClaimNumber(), "", c.getClaimDate()));
        }
        activities.sort(Comparator.comparing(Activity::getDate).reversed());

        model.addAttribute("totalPolicies", policies.size());
        model.addAttribute("activePolicies", activePolicies);
        model.addAttribute("claimsFiled", claims.size());
        model.addAttribute("totalPayments", totalPayments);
        model.addAttribute("typeCounts", typeCounts);
        model.addAttribute("colors", COLORS);
        model.addAttribute("gradient", gradient.toString());
        model.addAttribute("activities", activities.size() > 4 ? activities.subList(0, 4) : activities);
        return "dashboard";
    }
}
