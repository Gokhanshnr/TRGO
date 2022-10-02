package com.gokhan.trgo.repositories;

import com.gokhan.trgo.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}