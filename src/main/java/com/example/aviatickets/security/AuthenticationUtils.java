package com.example.aviatickets.security;

import com.example.aviatickets.model.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class AuthenticationUtils {

  public static Long getUserId() {
    return ((UDCustom) (SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).getUserId();
  }

  public static void updateRole(Role newRole) {
    var auth = SecurityContextHolder.getContext().getAuthentication();
    ((UDCustom) auth.getPrincipal()).setRole(newRole.name());
    Authentication newAuth = new UsernamePasswordAuthenticationToken(
        auth.getPrincipal(),
        auth.getCredentials(),
        List.of(new SimpleGrantedAuthority(newRole.name())));

    SecurityContextHolder.getContext().setAuthentication(newAuth);
  }
}
