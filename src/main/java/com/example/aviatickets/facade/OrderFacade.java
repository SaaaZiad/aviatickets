package com.example.aviatickets.facade;

import com.example.aviatickets.security.AuthenticationUtils;
import com.example.aviatickets.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@AllArgsConstructor
@Component
public class OrderFacade {
  private final OrderService orderService;
  public void getOrdersPage(Model model){
    var orders = orderService.findByUserId(AuthenticationUtils.getUserId());
    model.addAttribute("orders", orders);
  }
}
