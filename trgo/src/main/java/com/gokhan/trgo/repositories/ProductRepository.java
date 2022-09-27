package com.gokhan.trgo.repositories;

import com.gokhan.trgo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}