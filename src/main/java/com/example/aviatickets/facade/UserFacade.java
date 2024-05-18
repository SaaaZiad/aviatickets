package com.example.aviatickets.facade;


import com.example.aviatickets.model.User;
import com.example.aviatickets.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@AllArgsConstructor
@Component
public class UserFacade {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  public void setEmptyUser(Model model){
    var user = new User();
    model.addAttribute("user", user);
  }
  public boolean userExists(String username){
    return userService.findUserByUsername(username)!=null;
  }
  public void createUser(User user){
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userService.saveUser(user);
  }
}
