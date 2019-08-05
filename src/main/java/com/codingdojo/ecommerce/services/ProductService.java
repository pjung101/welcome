package com.codingdojo.ecommerce.services;

import java.util.Optional;



import java.util.List;

import com.codingdojo.ecommerce.models.Product;
import com.codingdojo.ecommerce.repositories.ProductRepo;

import org.springframework.stereotype.Service;
    @Service
   
    public class ProductService {
        
        private final ProductRepo productRepo;
        
        public ProductService(ProductRepo productRepo) {
            this.productRepo = productRepo;
        }
        
        public List<Product> allProducts() {
            return productRepo.findAll();
        }
        public Product createProduct(Product b) {
            return productRepo.save(b);
        }
        public Product findProduct(Long id) {
            Optional<Product> optionalProduct = productRepo.findById(id);
            if(optionalProduct.isPresent()) {
                return optionalProduct.get();
            } else {
                return null;
            }
        }
        public Product updateProduct(Product b) {
            return productRepo.save(b);
        }
    }