package com.example.aviatickets.exception;

public class NotEnoughTickets extends RuntimeException {
  public NotEnoughTickets(String message){
    super(message);
  }
}
