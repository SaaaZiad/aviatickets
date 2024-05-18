package com.example.aviatickets.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class CControllerAdvice {
  @ExceptionHandler(AccessDeniedException.class)
  public String handleError(){
    return "redirect:/admin/confirm";
  }
  @ExceptionHandler(NotEnoughMoney.class)
  public String lessMoney(){
    return "redirect:/account";
  }
  @ExceptionHandler(UserNotFound.class)
  public String userNotFound(){
    return "redirect:users/create";
  }
  @ExceptionHandler(IOException.class)
  public String errorsWithIo(IOException e){
    return "errors/not_found";
  }

}
