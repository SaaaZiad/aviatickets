package com.example.aviatickets.facade;


import com.example.aviatickets.dto.BuyTicketDTO;
import com.example.aviatickets.exception.NotEnoughMoney;
import com.example.aviatickets.exception.NotEnoughTickets;
import com.example.aviatickets.model.Ticket;
import com.example.aviatickets.model.Order;
import com.example.aviatickets.model.User;
import com.example.aviatickets.security.AuthenticationUtils;
import com.example.aviatickets.service.TicketService;
import com.example.aviatickets.service.OrderService;
import com.example.aviatickets.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@AllArgsConstructor
@Component
public class AvailableTicketsFacade {
  private final TicketService ticketService;
  private final OrderService orderService;
  private final UserService userService;

  public void getProductsPage(Model model) {
    model.addAttribute("tickets", ticketService.getTicketsAvailable());
  }

  public void getBuyPage(Model model, Long ticketId) {
    model.addAttribute("ticketId", ticketId);
    model.addAttribute("buyDTO", new BuyTicketDTO());
  }

  public void buyProduct(Long ticketId, BuyTicketDTO dto) throws NotEnoughTickets {
    Ticket ticket = ticketService.findById(ticketId);
    User user = userService.findUserById(AuthenticationUtils.getUserId());
    checkEnoughMoney(user, ticket, dto);
    checkEnoughAmount(ticket, dto);
    user.setBalance(user.getBalance()-dto.getAmount()* ticket.getPrice());
    ticket.setAmount(ticket.getAmount() - dto.getAmount());
    var order = createOrder(dto, user, ticket);
    ticket.getOrders().add(order);
    user.getOrders().add(order);
    userService.saveUser(user);
    ticketService.saveTicket(ticket);
    orderService.saveOrder(order);
  }

  private void checkEnoughMoney(User user, Ticket ticket, BuyTicketDTO dto) {
    if (user.getBalance() < ticket.getPrice() * dto.getAmount()) {
      throw new NotEnoughMoney("Not enough money to buy this amount");
    }
  }

  private void checkEnoughAmount(Ticket ticket, BuyTicketDTO dto){
    if (ticket.getAmount() < dto.getAmount()) {
      throw new NotEnoughTickets("Not enough tickets to confirm deal");
    }
  }

  private Order createOrder(BuyTicketDTO dto, User user, Ticket ticket){
    Order order = new Order();
    order.setAmount(dto.getAmount());
    order.setUser(user);
    order.setTicket(ticket);
    return order;
  }
}
