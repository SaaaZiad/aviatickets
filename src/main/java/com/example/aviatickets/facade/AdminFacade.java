package com.example.aviatickets.facade;


import com.example.aviatickets.dto.ConfirmAdminDTO;
import com.example.aviatickets.dto.UpdateAmountDTO;
import com.example.aviatickets.model.Ticket;
import com.example.aviatickets.model.Role;
import com.example.aviatickets.security.AuthenticationUtils;
import com.example.aviatickets.service.TicketService;
import com.example.aviatickets.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@AllArgsConstructor
@Component
@PropertySource("classpath:application.yml")
public class AdminFacade {
  private final TicketService ticketService;
  private final UserService userService;
  private final String secret = "adminpassword";

  public void getConfirmPage(Model model) {
    model.addAttribute("confirmAdminDTO", new ConfirmAdminDTO());
  }

  public void confirmAdmin(String confirmKey, Model model) {
    Long userId = AuthenticationUtils.getUserId();
    if (confirmKey.equals(secret)) {
      var user = userService.findUserById(userId);
      user.setRole(Role.ADMIN);
      userService.saveUser(user);
      AuthenticationUtils.updateRole(Role.ADMIN);
    } else {
      model.addAttribute("errorConfirming", "Wrong confirm code");
    }
  }

  public void getTicketsPage(Model model){
    model.addAttribute("tickets", ticketService.getTickets());
  }

  public void getCreateTicketPage(Model model) {
    model.addAttribute("ticket", new Ticket());
  }

  public void createTicket(Ticket ticket) throws Exception{
    ticketService.saveTicket(ticket);
  }
}
