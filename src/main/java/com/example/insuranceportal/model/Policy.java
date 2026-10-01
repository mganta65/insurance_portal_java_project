package com.example.insuranceportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String policyNumber;
    private String type;      // Health Insurance, Motor Insurance, Life Insurance, Travel Insurance
    private double premium;
    private String status;    // ACTIVE or EXPIRED

    public Policy() {}

    public Policy(String policyNumber, String type, double premium, String status) {
        this.policyNumber = policyNumber;
        this.type = type;
        this.premium = premium;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getPremium() { return premium; }
    public void setPremium(double premium) { this.premium = premium; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
