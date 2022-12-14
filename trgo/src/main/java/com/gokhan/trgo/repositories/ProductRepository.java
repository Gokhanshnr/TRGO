package com.gokhan.trgo.repositories;

import com.gokhan.trgo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByPidEquals(Integer pid);

    List<Product> findAll();

    boolean existsByPidEquals(Integer pid);


}