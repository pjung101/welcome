package com.codingdojo.ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import com.codingdojo.ecommerce.models.Category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {
    List<Category> findAll();
    Optional<Category> findByName(String name);
}