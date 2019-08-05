package com.codingdojo.ecommerce.services;

import java.util.Optional;
import java.util.List;

import com.codingdojo.ecommerce.models.Category;
import com.codingdojo.ecommerce.repositories.CategoryRepo;

import org.springframework.stereotype.Service;
    @Service
    public class CategoryService {
        private final CategoryRepo categoryRepo;
        
        public CategoryService(CategoryRepo categoryRepo) {
            this.categoryRepo = categoryRepo;
        }
        public List<Category> allCategories() {
            return categoryRepo.findAll();
        }
        public Category createCategory(Category b) {
            return categoryRepo.save(b);
        }
        public Category findCategory(Long id) {
            Optional<Category> optionalCategory = categoryRepo.findById(id);
            if(optionalCategory.isPresent()) {
                return optionalCategory.get();
            } else {
                return null;
            }
        }
        public Category findCategoryByName(String name) {
            Optional<Category> optionalCategory = categoryRepo.findByName(name);
            if(optionalCategory.isPresent()) {
                return optionalCategory.get();
            } else {
                return null;
            }
        }
        public Category updateCategory(Category b) {
            return categoryRepo.save(b);
        }
    }