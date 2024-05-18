package com.example.aviatickets.service;

import com.example.aviatickets.model.Order;
import com.example.aviatickets.repositories.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
  private final OrderRepo orderRepo;
  public void saveOrder(Order order){
    orderRepo.save(order);
  }
  public List<Order> findByUserId(Long userId){
    return orderRepo.findOrdersByUserId(userId);
  }
}
