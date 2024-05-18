package com.example.aviatickets.controllers;


import com.example.aviatickets.dto.ConfirmAdminDTO;
import com.example.aviatickets.facade.AdminFacade;
import com.example.aviatickets.model.Ticket;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
  private final AdminFacade adminFacade;

  @GetMapping("/confirm")
  public String getConfirmPage(Model model) {
    adminFacade.getConfirmPage(model);
    return "admin/admin_confirm";
  }

  @PostMapping("/confirm")
  public String confirmAdmin(Model model,
                             @ModelAttribute("confirmAdminDTO") @Valid ConfirmAdminDTO confirmAdminDTO,
                             BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "admin/admin_confirm";
    }
    adminFacade.confirmAdmin(confirmAdminDTO.getConfirmKey(), model);
    if (model.getAttribute("errorConfirming") != null) {
      return "admin/admin_confirm";
    } else {
      return "redirect:/admin/available_tickets";
    }
  }

  @GetMapping
  public String getMainPage() {
    return "admin/available_tickets";
  }

  @GetMapping("/available_tickets")
  public String getTicketsPage(Model model) {
    adminFacade.getTicketsPage(model);
    return "admin/admin_available_tickets";
  }

  @GetMapping("/available_tickets/create")
  public String getCreateTicketPage(Model model) {
    adminFacade.getCreateTicketPage(model);
    return "admin/admin_create_ticket";
  }

  @PostMapping("/available_tickets/create")
  public String createTicket(@ModelAttribute("ticket") @Valid Ticket ticket,
                             BindingResult bindingResult) throws Exception {
    if (bindingResult.hasErrors()) {
      return "admin/admin_create_ticket";
    }
    adminFacade.createTicket(ticket);
    return "redirect:/admin/available_tickets";
  }


}