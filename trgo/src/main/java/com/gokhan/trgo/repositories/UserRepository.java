package com.gokhan.trgo.repositories;

import com.gokhan.trgo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailEqualsIgnoreCase(String email);

    boolean existsByIdEquals(Long id);



}