package com.example.aviatickets.controllers;


import com.example.aviatickets.facade.OrderFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/orders")
public class OrderController {
  private final OrderFacade orderFacade;
  @GetMapping
  public String getOrdersPage(Model model){
    orderFacade.getOrdersPage(model);
    return "orders";
  }
}
