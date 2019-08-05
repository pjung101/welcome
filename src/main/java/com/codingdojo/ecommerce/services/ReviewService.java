package com.codingdojo.ecommerce.services;

import java.util.Optional;
import java.util.List;

import com.codingdojo.ecommerce.models.Review;
import com.codingdojo.ecommerce.repositories.ReviewRepo;

import org.springframework.stereotype.Service;
    @Service
    public class ReviewService {
        private final ReviewRepo reviewRepo;
        
        public ReviewService(ReviewRepo reviewRepo) {
            this.reviewRepo = reviewRepo;
        }
        public List<Review> allCategories() {
            return reviewRepo.findAll();
        }
        public Review createReview(Review b) {
            return reviewRepo.save(b);
        }
        public Review findReview(Long id) {
            Optional<Review> optionalReview = reviewRepo.findById(id);
            if(optionalReview.isPresent()) {
                return optionalReview.get();
            } else {
                return null;
            }
        }
        public Review updateReview(Review b) {
            return reviewRepo.save(b);
        }
    }