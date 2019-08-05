package com.codingdojo.ecommerce.repositories;

import java.util.List;

import com.codingdojo.ecommerce.models.ShippingAddress;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingAddressRepo extends CrudRepository<ShippingAddress, Long>{
    List<ShippingAddress> findAll();
}