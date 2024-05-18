package com.example.aviatickets.exception;

public class NotEnoughMoney extends RuntimeException{
  public NotEnoughMoney(String message){
    super(message);
  }
}
