package com.example.aviatickets.repositories;

import com.example.aviatickets.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
  User findByUserName(String userName);
}
