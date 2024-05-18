
package com.example.aviatickets.controllers;
import com.example.aviatickets.dto.TopUpAccountDTO;
import com.example.aviatickets.facade.AccountFacade;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/account")
public class AccountController {
  private final AccountFacade accountFacade;
  @GetMapping
  public String getUserAccount(Model model){
    accountFacade.getUserAccount(model);
    return "account";
  }
  @GetMapping("/top-up/{userId}")
  public String getTopUpPage(@PathVariable("userId") Long userId, Model model){
    accountFacade.getTopUpPage(model, userId);
    return "account_topup";
  }
  @PostMapping("/top-up/{userId}")
  public String topUp(@PathVariable("userId") Long userId,
                      Model model,
                      @ModelAttribute("topUpAccountDTO") @Valid TopUpAccountDTO topUpSum,
                      BindingResult bindingResult){
    if(bindingResult.hasErrors()){
      accountFacade.setUser(model, userId);
      return "account_topup";
    }
    accountFacade.topUp(topUpSum, userId);
    return "redirect:/account";
  }
}
