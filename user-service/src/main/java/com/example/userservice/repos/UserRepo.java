package com.example.userservice.repos;

import com.example.userservice.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findUserByEmail(String email);
}
