package com.xdlab.taskmaster.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;


import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
