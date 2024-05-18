package com.example.aviatickets.facade;

import com.example.aviatickets.dto.TopUpAccountDTO;
import com.example.aviatickets.security.AuthenticationUtils;
import com.example.aviatickets.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@AllArgsConstructor
@Component
public class AccountFacade {
  private final UserService userService;
  public void getUserAccount(Model model){
    Long userId = AuthenticationUtils.getUserId();
    model.addAttribute("user",  userService.findUserById(userId));
  }
  public void setUser(Model model, Long userId){
    model.addAttribute("user", userService.findUserById(userId));
  }
  public void getTopUpPage(Model model, Long userId){
    setUser(model, userId);
    model.addAttribute("topUpAccountDTO", new TopUpAccountDTO(0L));
  }
  public void topUp(TopUpAccountDTO topUpAccountDTO, Long userId){
    var user = userService.findUserById(userId);
    user.setBalance(user.getBalance()+topUpAccountDTO.getTopUpSum());
    userService.saveUser(user);
  }

}
