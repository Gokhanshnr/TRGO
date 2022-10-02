package com.gokhan.trgo.services;

import com.gokhan.trgo.entities.Orders;
import com.gokhan.trgo.entities.Pictures;
import com.gokhan.trgo.repositories.OrdersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class OrdersService {

    final OrdersRepository ordersRepository;


    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public ResponseEntity add(Orders order) {
        ordersRepository.save(order);
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", order);
        return new ResponseEntity(hm, HttpStatus.OK);
    }
}
