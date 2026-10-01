package com.example.insuranceportal.config;

import com.example.insuranceportal.model.Claim;
import com.example.insuranceportal.model.Payment;
import com.example.insuranceportal.model.Policy;
import com.example.insuranceportal.repository.ClaimRepository;
import com.example.insuranceportal.repository.PaymentRepository;
import com.example.insuranceportal.repository.PolicyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

/** Loads sample data into the in-memory database every time the app starts. */
@Configuration
public class DataLoader {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Bean
    public CommandLineRunner loadData(PolicyRepository policies, ClaimRepository claims, PaymentRepository payments) {
        return args -> {
            policies.save(new Policy("POL-1001", "Health Insurance", 12500, "ACTIVE"));
            policies.save(new Policy("POL-1002", "Health Insurance", 15000, "ACTIVE"));
            policies.save(new Policy("POL-1003", "Motor Insurance", 8250, "ACTIVE"));
            policies.save(new Policy("POL-1004", "Life Insurance", 13000, "EXPIRED"));

            claims.save(new Claim("CLM12345", "POL-1001", 20000, "SUBMITTED", LocalDate.of(2024, 5, 18)));
            claims.save(new Claim("CLM12346", "POL-1003", 9000, "APPROVED", LocalDate.of(2024, 4, 2)));

            payments.save(new Payment("Health Insurance Premium", 12500, LocalDate.of(2024, 5, 20)));
            payments.save(new Payment("Health Insurance Premium", 15000, LocalDate.of(2024, 3, 15)));
            payments.save(new Payment("Motor Insurance Premium", 8250, LocalDate.of(2024, 2, 10)));
            payments.save(new Payment("Life Insurance Premium", 13000, LocalDate.of(2024, 1, 5)));

            log.info("Sample data loaded: {} policies, {} claims, {} payments",
                    policies.count(), claims.count(), payments.count());
        };
    }
}
