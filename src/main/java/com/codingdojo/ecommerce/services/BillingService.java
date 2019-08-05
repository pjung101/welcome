package com.codingdojo.ecommerce.services;

import java.util.Optional;
import java.util.List;

import com.codingdojo.ecommerce.models.Billing;
import com.codingdojo.ecommerce.repositories.BillingRepo;

import org.springframework.stereotype.Service;
    @Service
    public class BillingService {
        private final BillingRepo billingRepo;
        
        public BillingService(BillingRepo billingRepo) {
            this.billingRepo = billingRepo;
        }
        public List<Billing> allCategories() {
            return billingRepo.findAll();
        }
        public Billing createBilling(Billing b) {
            return billingRepo.save(b);
        }
        public Billing findBilling(Long id) {
            Optional<Billing> optionalBilling = billingRepo.findById(id);
            if(optionalBilling.isPresent()) {
                return optionalBilling.get();
            } else {
                return null;
            }
        }
        public Billing updateBilling(Billing b) {
            return billingRepo.save(b);
        }
    }