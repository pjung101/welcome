package com.codingdojo.ecommerce.repositories;

import java.util.List;

import com.codingdojo.ecommerce.models.Vendor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepo extends CrudRepository<Vendor, Long>{
    List<Vendor> findAll();
    Vendor findByEmail(String email);

}