package com.gokhan.trgo.restcontrollers;

import com.gokhan.trgo.entities.Location;
import com.gokhan.trgo.entities.Product;
import com.gokhan.trgo.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductRestController {
    final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Product product) {
        return productService.add(product);
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return productService.list();
    }


}
