package com.gokhan.trgo.restcontrollers;

import com.gokhan.trgo.entities.Orders;
import com.gokhan.trgo.services.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrdersRestConrtoller {


    final OrdersService ordersService;

    public OrdersRestConrtoller(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Orders order) {
        return ordersService.add(order);
    }
}
