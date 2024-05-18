package com.example.aviatickets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
    return httpSecurity
        .csrf(csrf -> csrf.disable())
        .cors(cors -> cors.disable())
        .authorizeHttpRequests((request)->{
          request
              .requestMatchers("/").permitAll()
              .requestMatchers("/users/**").permitAll()
              .requestMatchers("/static/**").permitAll()
              .requestMatchers("/admin/confirm").authenticated()
              .requestMatchers("/admin/**").hasAuthority("ADMIN")
              .anyRequest().authenticated();
        })
        .formLogin(form->{
          form
              .loginPage("/users/authorization")
              .loginProcessingUrl("/users/perform-authorization");
        })
        .logout(logout->{
          logout.logoutUrl("/users/logout");
        })
        .exceptionHandling(eh->{
          eh.accessDeniedHandler(((request, response, accessDeniedException) -> {
            response.sendRedirect("/admin/confirm");
          }));
        })
        .httpBasic(Customizer.withDefaults())
        .build();
  }
}
