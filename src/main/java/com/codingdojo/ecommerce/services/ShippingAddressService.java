package com.codingdojo.ecommerce.services;

import java.util.Optional;
import java.util.List;

import com.codingdojo.ecommerce.models.ShippingAddress;
import com.codingdojo.ecommerce.repositories.ShippingAddressRepo;

import org.springframework.stereotype.Service;
    @Service
    public class ShippingAddressService {
        private final ShippingAddressRepo shippingAddressRepo;
        
        public ShippingAddressService(ShippingAddressRepo shippingAddressRepo) {
            this.shippingAddressRepo = shippingAddressRepo;
        }
        public List<ShippingAddress> allCategories() {
            return shippingAddressRepo.findAll();
        }
        public ShippingAddress createShippingAddress(ShippingAddress b) {
            return shippingAddressRepo.save(b);
        }
        public ShippingAddress findShippingAddress(Long id) {
            Optional<ShippingAddress> optionalShippingAddress = shippingAddressRepo.findById(id);
            if(optionalShippingAddress.isPresent()) {
                return optionalShippingAddress.get();
            } else {
                return null;
            }
        }
        public ShippingAddress updateShippingAddress(ShippingAddress b) {
            return shippingAddressRepo.save(b);
        }
    }