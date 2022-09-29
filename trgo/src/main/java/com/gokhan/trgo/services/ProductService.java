package com.gokhan.trgo.services;

import com.gokhan.trgo.entities.Location;
import com.gokhan.trgo.entities.Product;
import com.gokhan.trgo.repositories.ProductRepository;
import com.gokhan.trgo.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ResponseEntity add(Product product) {
        Map<REnum, Object> hm = new LinkedHashMap();
        Product sProduct = productRepository.save(product);
        hm.put(REnum.status, true);
        hm.put(REnum.result, sProduct);
        return new ResponseEntity( hm , HttpStatus.OK);
    }


    public ResponseEntity list() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<Product> productsList = productRepository.findAll();
        hm.put(REnum.status, true);
        hm.put(REnum.result, productsList);
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }


    public ResponseEntity get(Integer id){
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Optional<Product> product = productRepository.findByPidEquals(id);
        hm.put(REnum.status, true);
        hm.put(REnum.result, product);
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }

}
