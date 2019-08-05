package com.codingdojo.ecommerce.repositories;

import java.util.List;

import com.codingdojo.ecommerce.models.Review;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends CrudRepository<Review, Long>{
    List<Review> findAll();
}