package com.example.aviatickets.service;


import com.example.aviatickets.exception.NotFound;
import com.example.aviatickets.model.Ticket;
import com.example.aviatickets.repositories.TicketRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TicketService {
  private final TicketRepo ticketRepo;

  public List<Ticket> getTickets() {
    return ticketRepo.findAll();
  }

  public List<Ticket> getTicketsAvailable(){
    return ticketRepo.findAllAvailable();
  }

  public void saveTicket(Ticket ticket) {
    ticketRepo.save(ticket);
  }

  public Ticket findById(Long ticketId) {
    return ticketRepo.findById(ticketId).orElseThrow(() -> new NotFound("Error getting ticket"));
  }


}
