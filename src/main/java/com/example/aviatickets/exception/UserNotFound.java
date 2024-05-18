package com.example.aviatickets.exception;

public class UserNotFound extends RuntimeException{
  public UserNotFound(String message){
    super(message);
  }
}
