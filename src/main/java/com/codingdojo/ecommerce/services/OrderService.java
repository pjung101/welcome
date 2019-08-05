package com.codingdojo.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.codingdojo.ecommerce.models.Order;
import com.codingdojo.ecommerce.repositories.OrderRepo;

import org.springframework.stereotype.Service;

@Service
   
public class OrderService {

    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
        
        public List<Order> allOrders() {
            return orderRepo.findAll();
        }
        public Order createOrder(Order b) {
            return orderRepo.save(b);
        }
        public Order findOrder(Long id) {
            Optional<Order> optionalOrder = orderRepo.findById(id);
            if(optionalOrder.isPresent()) {
                return optionalOrder.get();
            } else {
                return null;
            }
        }
        public Order updateOrder(Order b) {
            return orderRepo.save(b);
        }
    }