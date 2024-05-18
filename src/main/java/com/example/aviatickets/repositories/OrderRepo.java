package com.example.aviatickets.repositories;

import com.example.aviatickets.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
  @Query("select o from Order o where o.user.userId=?1")
  List<Order> findOrdersByUserId(Long userId);
}
