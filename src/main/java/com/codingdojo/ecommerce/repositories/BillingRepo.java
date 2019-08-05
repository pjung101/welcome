package com.codingdojo.ecommerce.repositories;

import java.util.List;

import com.codingdojo.ecommerce.models.Billing;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepo extends CrudRepository<Billing, Long>{
    List<Billing> findAll();
}