package com.codingdojo.ecommerce.services;

import java.util.Optional;
import java.util.List;

import com.codingdojo.ecommerce.models.Vendor;
import com.codingdojo.ecommerce.repositories.VendorRepo;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class VendorService {
    private final VendorRepo vendorRepo;
    
    public VendorService(VendorRepo vendorRepo) {
        this.vendorRepo = vendorRepo;
    }
    public List<Vendor> allCategories() {
        return vendorRepo.findAll();
    }
    public Vendor createVendor(Vendor b) {
        return vendorRepo.save(b);
    }
    public Vendor findVendor(Long id) {
        Optional<Vendor> optionalVendor = vendorRepo.findById(id);
        if(optionalVendor.isPresent()) {
            return optionalVendor.get();
        } else {
            return null;
        }
    }
    public Vendor updateVendor(Vendor b) {
        return vendorRepo.save(b);
    }
    public Vendor registerVendor(Vendor vendor) {
        String hashed = BCrypt.hashpw(vendor.getPassword(), BCrypt.gensalt());
        vendor.setPassword(hashed);
        return vendorRepo.save(vendor);
    }
    public Vendor findByEmail(String email) {
        return vendorRepo.findByEmail(email);
    }

    public Boolean authenticateVendor(String email, String password) {
        Vendor vendor = vendorRepo.findByEmail(email);
        if(vendor==null) {
            return false; 
        }
        else {
            if(BCrypt.checkpw(password, vendor.getPassword())) {
                return true;
            }
            else {
                return false;
            }
        }
    }
}