package com.example.aviatickets.controllers;


import com.example.aviatickets.facade.UserFacade;
import com.example.aviatickets.model.User;
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
@RequestMapping("/users")
public class UserController {
  private final UserFacade userFacade;

  @GetMapping("/authorization")
  public String getAuthPage(){
    return "authorization";
  }

  @GetMapping("/create")
  public String getUserCreatePage(Model model) {
    userFacade.setEmptyUser(model);
    return "create_acc";
  }

  @PostMapping("/create")
  public String getUserCreatePage(Model model, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
      return "create_acc";
    }
    if(userFacade.userExists(user.getUserName())){
      model.addAttribute("error", "Данное имя пользователя уже занято");
      return "create_acc";
    }
    userFacade.createUser(user);
    return "redirect:/users/authorization";
  }
}
