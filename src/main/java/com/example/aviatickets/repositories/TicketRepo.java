package com.example.aviatickets.repositories;

import com.example.aviatickets.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long>{
  @Query("select f from Ticket f where f.amount>0")
  List<Ticket> findAllAvailable();
}
