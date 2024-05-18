package com.example.aviatickets.service;

import com.example.aviatickets.security.UDCustom;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UDSCustom implements UserDetailsService {
  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var userFound = userService.findUserByUsername(username);
    return userFound == null ? null : new UDCustom(userFound);
  }
}
